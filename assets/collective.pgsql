--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: calc_score(json); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.calc_score(votes json) RETURNS numeric
    LANGUAGE plpgsql
    AS $$
DECLARE
	upvoters bigint[];
	downvoters bigint[];
	score integer;
BEGIN
	SELECT array_agg(upvotes::bigint) FROM LATERAL json_array_elements_text(votes->'upvotes') AS upvotes GROUP BY id INTO upvoters;
	SELECT array_agg(downvotes::bigint) FROM LATERAL json_array_elements_text(votes->'downvotes') AS downvotes GROUP BY id INTO downvoters;
	score := array_length(upvoters, 1) - array_length(downvoters, 1);
	RETURN score;
END
$$;


ALTER FUNCTION public.calc_score(votes json) OWNER TO postgres;

--
-- Name: compare_json(json, json); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.compare_json(json1 json, json2 json) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
DECLARE
	keys1 text[];
	keys2 text[];
	array1 json[];
	array2 json[];
	type1 text;
	type2 text;
	match_found boolean;
BEGIN
	type1 := json_typeof(json1);
	type2 := json_typeof(json2);
	
	IF type1 != type2 THEN
		RETURN FALSE;
	END IF;
	IF type1 = 'number' OR type1 = 'string' OR type1 = 'boolean' OR type1 = 'null' THEN
		IF json1::text != json2::text THEN	
			RETURN FALSE;
		END IF;
	ELSEIF type1 = 'array' THEN
		SELECT array_agg(obj) FROM LATERAL json_array_elements(json1) AS obj INTO array1;
		SELECT array_agg(obj) FROM LATERAL json_array_elements(json2) AS obj INTO array2;
		FOR x IN 1..array_upper(array1, 1) LOOP
			IF NOT compare_json(array1[x], array2[x]) THEN
				RETURN FALSE;
			END IF;
		END LOOP;
	ELSEIF type1 = 'object' THEN
		SELECT array_agg(keys) FROM json_object_keys(json1::json) AS keys INTO keys1;
		SELECT array_agg(keys) FROM json_object_keys(json2::json) AS keys INTO keys2;

		--keys are always a unique string
		SELECT ARRAY(SELECT unnest(keys1) ORDER BY 1) INTO keys1;
		SELECT ARRAY(SELECT unnest(keys2) ORDER BY 1) INTO keys2;
	
		IF array_length(keys1, 1) != array_length(keys2, 1) THEN
			RETURN FALSE;
		END IF;

		FOR x IN 1..array_upper(keys1, 1) LOOP
			IF keys1[x] != keys2[x] THEN
				RETURN FALSE;
			END IF;
		END LOOP;
		FOR x IN 1..array_upper(keys1, 1) LOOP
			--RAISE NOTICE '%, % - %, % JSON COMP', json1->keys1[x], json_typeof(json1->keys1[x]), json2->keys2[x], json_typeof(json2->keys2[x]);
			IF COMPARE_JSON(json1->keys1[x], json2->keys2[x]) != TRUE THEN
				RETURN FALSE;
			END IF;
		END LOOP;
	END IF;
	RETURN TRUE;
END
$$;


ALTER FUNCTION public.compare_json(json1 json, json2 json) OWNER TO postgres;

--
-- Name: get_latest(json); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_latest(history json) RETURNS numeric
    LANGUAGE plpgsql
    AS $$
DECLARE
	latest timestamp;
BEGIN
	SELECT json_object_keys::timestamp FROM json_object_keys(history) INTO latest ORDER BY times desc LIMIT 1;
	RETURN latest;
END
$$;


ALTER FUNCTION public.get_latest(history json) OWNER TO postgres;

--
-- Name: =; Type: OPERATOR; Schema: public; Owner: postgres
--

CREATE OPERATOR public.= (
    FUNCTION = public.compare_json,
    LEFTARG = json,
    RIGHTARG = json,
    COMMUTATOR = OPERATOR(public.=),
    NEGATOR = OPERATOR(public.<>),
    MERGES,
    HASHES
);


ALTER OPERATOR public.= (json, json) OWNER TO postgres;

--
-- Name: btree_json; Type: OPERATOR FAMILY; Schema: public; Owner: postgres
--

CREATE OPERATOR FAMILY public.btree_json USING btree;


ALTER OPERATOR FAMILY public.btree_json USING btree OWNER TO postgres;

--
-- Name: gist_json_ops; Type: OPERATOR FAMILY; Schema: public; Owner: postgres
--

CREATE OPERATOR FAMILY public.gist_json_ops USING gist;


ALTER OPERATOR FAMILY public.gist_json_ops USING gist OWNER TO postgres;

--
-- Name: hash_json; Type: OPERATOR FAMILY; Schema: public; Owner: postgres
--

CREATE OPERATOR FAMILY public.hash_json USING hash;


ALTER OPERATOR FAMILY public.hash_json USING hash OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: artworks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artworks (
    id bigint NOT NULL,
    file_type text,
    title text,
    owner bigint,
    submitted date
);


ALTER TABLE public.artworks OWNER TO postgres;

--
-- Data for Name: artworks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.artworks (id, file_type, title, owner, submitted) FROM stdin;
101	gif	101	100	2010-12-25
139	jpg	139	100	2011-10-26
141	jpg	141	100	2011-11-25
142	jpg	142	100	2012-03-30
143	jpg	143	100	2012-06-01
102	jpg	102	100	2012-08-26
145	jpg	145	100	2014-10-01
146	jpg	146	100	2014-10-15
147	jpg	147	101	2014-11-11
148	jpg	148	101	2015-03-13
149	jpg	149	101	2015-09-18
150	jpg	150	101	2015-11-10
151	jpg	151	101	2016-08-24
152	jpg	152	101	2016-08-27
135	jpg	135	101	2017-04-11
136	jpg	136	101	2017-12-17
138	jpg	138	101	2018-04-16
140	jpg	140	101	2018-06-21
144	jpg	144	101	2018-06-30
137	jpg	137	101	2018-07-10
103	jpg	103	102	2018-08-15
104	gif	104	102	2018-09-04
105	jpg	105	102	2019-08-14
106	gif	106	102	2019-08-15
107	jpg	107	102	2020-01-25
108	gif	108	102	2011-03-20
109	jpg	109	102	2012-04-09
110	jpg	110	102	2012-08-05
111	jpg	111	102	2013-01-14
112	jpg	112	103	2013-02-14
113	jpg	113	103	2013-04-03
114	jpg	114	103	2013-04-11
115	jpg	115	103	2013-05-11
116	jpg	116	103	2014-04-08
117	jpg	117	103	2014-06-27
118	jpg	118	103	2015-05-14
119	jpg	119	103	2016-07-27
120	jpg	120	103	2016-09-19
121	jpg	121	104	2016-09-27
122	jpg	122	104	2016-11-18
123	jpg	123	104	2016-12-03
124	jpg	124	104	2017-07-04
125	jpg	125	104	2017-08-16
126	jpg	126	104	2018-10-09
127	jpg	127	104	2018-11-26
128	jpg	128	104	2019-05-26
129	jpg	129	104	2019-06-20
130	jpg	130	104	2019-07-02
131	jpg	131	105	2019-08-09
132	jpg	132	105	2019-11-16
133	jpg	133	105	2016-08-07
134	jpg	134	105	2016-11-01
100	gif	100	105	2017-04-23
\.


--
-- Name: artworks artwork_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artworks
    ADD CONSTRAINT artwork_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

