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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: staff_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.staff_roles (
    role_id bigint NOT NULL,
    name text,
    permissions json
);


ALTER TABLE public.staff_roles OWNER TO postgres;

--
-- Name: staff_roles_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.staff_roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.staff_roles_role_id_seq OWNER TO postgres;

--
-- Name: staff_roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.staff_roles_role_id_seq OWNED BY public.staff_roles.role_id;


--
-- Name: trade_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.trade_roles (
    role_id bigint NOT NULL,
    parent_role bigint,
    name text,
    description text
);


ALTER TABLE public.trade_roles OWNER TO postgres;

--
-- Name: trade_roles_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.trade_roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.trade_roles_role_id_seq OWNER TO postgres;

--
-- Name: trade_roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.trade_roles_role_id_seq OWNED BY public.trade_roles.role_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    username text,
    staff_roles bigint[],
    trade_roles bigint[],
    icon_url text,
    settings json,
    payment_info json,
    profile_card text,
    profile_page text,
    join_date timestamp with time zone,
    login_history json[],
    social_media json,
    custom_url text
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: staff_roles role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff_roles ALTER COLUMN role_id SET DEFAULT nextval('public.staff_roles_role_id_seq'::regclass);


--
-- Name: trade_roles role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trade_roles ALTER COLUMN role_id SET DEFAULT nextval('public.trade_roles_role_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: staff_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.staff_roles (role_id, name, permissions) FROM stdin;
\.


--
-- Data for Name: trade_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.trade_roles (role_id, parent_role, name, description) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, username, staff_roles, trade_roles, icon_url, settings, payment_info, profile_card, profile_page, join_date, login_history, social_media, custom_url) FROM stdin;
1	terabix	\N	\N	\N	\N	\N	\N	<b>Hello World!</b>	\N	\N	\N	\N
\.


--
-- Name: staff_roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.staff_roles_role_id_seq', 1, false);


--
-- Name: trade_roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.trade_roles_role_id_seq', 1, false);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 1, true);


--
-- Name: staff_roles staff_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff_roles
    ADD CONSTRAINT staff_roles_pkey PRIMARY KEY (role_id);


--
-- Name: trade_roles trade_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trade_roles
    ADD CONSTRAINT trade_roles_pkey PRIMARY KEY (role_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: trade_roles trade_roles_parent_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trade_roles
    ADD CONSTRAINT trade_roles_parent_role_fkey FOREIGN KEY (parent_role) REFERENCES public.trade_roles(role_id);


--
-- PostgreSQL database dump complete
--

