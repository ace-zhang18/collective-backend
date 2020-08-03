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
-- Name: art_tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.art_tags (
    tag_id bigint NOT NULL,
    name text NOT NULL,
    parent bigint
);


ALTER TABLE public.art_tags OWNER TO postgres;

--
-- Name: artworks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artworks (
    artwork_id bigint NOT NULL,
    owners json,
    permissions json,
    history json,
    content text,
    sale json[],
    tags bigint[],
    file_type text,
    title text
);


ALTER TABLE public.artworks OWNER TO postgres;

--
-- Name: artwork_artwork_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.artwork_artwork_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.artwork_artwork_id_seq OWNER TO postgres;

--
-- Name: artwork_artwork_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.artwork_artwork_id_seq OWNED BY public.artworks.artwork_id;


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

ALTER TABLE ONLY public.artworks ALTER COLUMN artwork_id SET DEFAULT nextval('public.artwork_artwork_id_seq'::regclass);


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
-- Data for Name: art_tags; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.art_tags (tag_id, name, parent) FROM stdin;
100	Digital Art	0
101	Traditional Art	0
102	Literature	0
103	Voice Acting	0
104	3D	0
105	Animation	0
106	Film	0
107	Modeling	0
108	Photography	0
109	Artisan Crafts	0
110	Coding and Development	0
111	Resources and Stock	0
112	Game Design	0
156	Political	102
157	Fantasy	102
158	Horror	102
113	Sci_fi	100
114	Concept Art	100
115	Illustration	100
116	Political	100
117	Pop Art	100
118	Fantasy	100
119	Horror	100
120	Surreal	100
121	Landscape	100
122	Emoticons	100
123	Anime	100
124	Manga	100
125	Comics	100
126	Vectors	100
127	Fashion	100
128	Logos	100
129	Industrial	100
130	Architecture	100
131	Wallpaper	100
132	Character Design	100
133	Fan Art	100
134	Costumes	100
135	Other	100
136	Visual Novel	100
159	Comics	102
160	Fan fiction	102
161	Poetry	102
162	Scripts	102
163	Novel	102
164	Visual Novel	102
165	Scripts	103
166	Anime	103
167	Cartoon	103
168	Films	103
169	Games	103
170	Movies	103
171	Sci-fi	104
172	Concept Art	104
173	Fantasy	104
174	Horror	104
175	Anime	104
176	Fashion	104
137	Sci-fi	101
138	Concept Art	101
139	Illustration	101
140	Political	101
141	Pop Art	101
142	Fantasy	101
143	Horror	101
144	Surreal	101
145	Landscape	101
146	Anime	101
147	Manga	101
148	Comics	101
149	Fashion	101
150	Logos	101
151	Architecture	101
152	Tattoo	101
153	Character Design	101
154	Costumes	101
155	Fan Art	101
177	Architecture	104
178	Character Design	104
179	Fan Art	104
\.


--
-- Data for Name: artworks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.artworks (artwork_id, owners, permissions, history, content, sale, tags, file_type, title) FROM stdin;
100	{"users":[1]}	\N	\N	\N	\N	\N	jpg	Puppeteer
101	{"users":[1]}	\N	\N	\N	\N	\N	jpg	Crow
102	{"users":[1]}	\N	\N	\N	\N	\N	jpg	Shrouds
103	{"users":[1]}	\N	\N	\N	\N	\N	jpg	Couple
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
-- Name: artwork_artwork_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.artwork_artwork_id_seq', 1, false);


--
-- Name: forums_forum_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.forums_forum_id_seq', 2, true);


--
-- Name: posts_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.posts_post_id_seq', 1, false);


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
-- Name: art_tags art_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.art_tags
    ADD CONSTRAINT art_tags_pkey PRIMARY KEY (tag_id);


--
-- Name: artworks artwork_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artworks
    ADD CONSTRAINT artwork_pkey PRIMARY KEY (artwork_id);


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

