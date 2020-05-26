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
-- Name: artworks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artworks (
    artwork_id bigint NOT NULL,
    owners json,
    permissions json,
    history json,
    sale json[],
    tags bigint[],
    file_type text,
    metadata json,
    title text
);


ALTER TABLE public.artworks OWNER TO postgres;

--
-- Name: artworks_artwork_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.artworks_artwork_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.artworks_artwork_id_seq OWNER TO postgres;

--
-- Name: artworks_artwork_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.artworks_artwork_id_seq OWNED BY public.artworks.artwork_id;


--
-- Name: forums; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.forums (
    forum_id bigint NOT NULL,
    parent bigint,
    permissions json,
    name text
);


ALTER TABLE public.forums OWNER TO postgres;

--
-- Name: forums_forum_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.forums_forum_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.forums_forum_id_seq OWNER TO postgres;

--
-- Name: forums_forum_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.forums_forum_id_seq OWNED BY public.forums.forum_id;


--
-- Name: posts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.posts (
    post_id bigint NOT NULL,
    thread bigint,
    author bigint,
    history json,
    "timestamp" timestamp with time zone,
    title text,
    text text,
    rating json
);


ALTER TABLE public.posts OWNER TO postgres;

--
-- Name: posts_post_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.posts_post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.posts_post_id_seq OWNER TO postgres;

--
-- Name: posts_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.posts_post_id_seq OWNED BY public.posts.post_id;


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
-- Name: threads; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.threads (
    thread_id bigint NOT NULL,
    forum bigint,
    permissions json,
    title text,
    author bigint,
    "timestamp" timestamp with time zone
);


ALTER TABLE public.threads OWNER TO postgres;

--
-- Name: threads_thread_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.threads_thread_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.threads_thread_id_seq OWNER TO postgres;

--
-- Name: threads_thread_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.threads_thread_id_seq OWNED BY public.threads.thread_id;


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
-- Name: artworks artwork_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artworks ALTER COLUMN artwork_id SET DEFAULT nextval('public.artworks_artwork_id_seq'::regclass);


--
-- Name: forums forum_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forums ALTER COLUMN forum_id SET DEFAULT nextval('public.forums_forum_id_seq'::regclass);


--
-- Name: posts post_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts ALTER COLUMN post_id SET DEFAULT nextval('public.posts_post_id_seq'::regclass);


--
-- Name: staff_roles role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff_roles ALTER COLUMN role_id SET DEFAULT nextval('public.staff_roles_role_id_seq'::regclass);


--
-- Name: threads thread_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.threads ALTER COLUMN thread_id SET DEFAULT nextval('public.threads_thread_id_seq'::regclass);


--
-- Name: trade_roles role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trade_roles ALTER COLUMN role_id SET DEFAULT nextval('public.trade_roles_role_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: artworks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.artworks (artwork_id, owners, permissions, history, sale, tags, file_type, metadata, title) FROM stdin;
1	{"users":[0]}	\N	\N	\N	\N	jpg	\N	Staff of Flowing Water
3	{"users":[0]}	\N	\N	\N	\N	jpg	\N	Apple
4	{"users":[0]}	\N	\N	\N	\N	jpg	\N	Orange
\.


--
-- Data for Name: forums; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.forums (forum_id, parent, permissions, name) FROM stdin;
0	\N	\N	Main Hub
1	0	\N	Sub-interest 1
2	0	\N	Sub-interest 2
\.


--
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.posts (post_id, thread, author, history, "timestamp", title, text, rating) FROM stdin;
1	1	1	\N	2020-05-14 09:30:20-04	Eminem	Rap God	\N
2	1	1	\N	2020-05-16 09:30:20-04	Dr. Dre	The Doctor	\N
3	1	1	\N	2020-05-13 09:30:20-04	Snoop Dogg	High AF	\N
\.


--
-- Data for Name: staff_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.staff_roles (role_id, name, permissions) FROM stdin;
\.


--
-- Data for Name: threads; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.threads (thread_id, forum, permissions, title, author, "timestamp") FROM stdin;
1	0	\N	This Topic	1	2020-05-10 09:30:20-04
2	0	\N	That Topic	1	2020-04-10 09:30:20-04
3	0	\N	The Other Topic	1	2020-03-10 09:30:20-04
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
1	terabix	\N	\N	\N	\N	\N	\N	<h1><u>Jesus Christ</u></h1>	\N	\N	\N	\N
\.


--
-- Name: artworks_artwork_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.artworks_artwork_id_seq', 4, true);


--
-- Name: forums_forum_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.forums_forum_id_seq', 2, true);


--
-- Name: posts_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.posts_post_id_seq', 3, true);


--
-- Name: staff_roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.staff_roles_role_id_seq', 1, false);


--
-- Name: threads_thread_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.threads_thread_id_seq', 3, true);


--
-- Name: trade_roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.trade_roles_role_id_seq', 1, false);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 1, true);


--
-- Name: forums forums_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forums
    ADD CONSTRAINT forums_pkey PRIMARY KEY (forum_id);


--
-- Name: posts posts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (post_id);


--
-- Name: staff_roles staff_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff_roles
    ADD CONSTRAINT staff_roles_pkey PRIMARY KEY (role_id);


--
-- Name: threads threads_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.threads
    ADD CONSTRAINT threads_pkey PRIMARY KEY (thread_id);


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
-- Name: forums forums_parent_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forums
    ADD CONSTRAINT forums_parent_fkey FOREIGN KEY (parent) REFERENCES public.forums(forum_id);


--
-- Name: posts posts_author_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_author_fkey FOREIGN KEY (author) REFERENCES public.users(user_id);


--
-- Name: posts posts_thread_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_thread_fkey FOREIGN KEY (thread) REFERENCES public.threads(thread_id);


--
-- Name: threads threads_author_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.threads
    ADD CONSTRAINT threads_author_fkey FOREIGN KEY (author) REFERENCES public.users(user_id);


--
-- Name: threads threads_forum_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.threads
    ADD CONSTRAINT threads_forum_fkey FOREIGN KEY (forum) REFERENCES public.forums(forum_id);


--
-- Name: trade_roles trade_roles_parent_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trade_roles
    ADD CONSTRAINT trade_roles_parent_role_fkey FOREIGN KEY (parent_role) REFERENCES public.trade_roles(role_id);


--
-- PostgreSQL database dump complete
--

