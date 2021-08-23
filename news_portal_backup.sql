--
-- PostgreSQL database dump
--

-- Dumped from database version 12.7 (Ubuntu 12.7-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.7 (Ubuntu 12.7-0ubuntu0.20.04.1)

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
-- Name: departments; Type: TABLE; Schema: public; Owner: moringa
--

CREATE TABLE public.departments (
    id integer NOT NULL,
    name character varying,
    description character varying,
    numberofemployees integer
);


ALTER TABLE public.departments OWNER TO moringa;

--
-- Name: departments_id_seq; Type: SEQUENCE; Schema: public; Owner: moringa
--

CREATE SEQUENCE public.departments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.departments_id_seq OWNER TO moringa;

--
-- Name: departments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: moringa
--

ALTER SEQUENCE public.departments_id_seq OWNED BY public.departments.id;


--
-- Name: departments_news; Type: TABLE; Schema: public; Owner: moringa
--

CREATE TABLE public.departments_news (
    id integer NOT NULL,
    departmentid integer,
    newsid integer
);


ALTER TABLE public.departments_news OWNER TO moringa;

--
-- Name: departments_news_id_seq; Type: SEQUENCE; Schema: public; Owner: moringa
--

CREATE SEQUENCE public.departments_news_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.departments_news_id_seq OWNER TO moringa;

--
-- Name: departments_news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: moringa
--

ALTER SEQUENCE public.departments_news_id_seq OWNED BY public.departments_news.id;


--
-- Name: news; Type: TABLE; Schema: public; Owner: moringa
--

CREATE TABLE public.news (
    id integer NOT NULL,
    post character varying
);


ALTER TABLE public.news OWNER TO moringa;

--
-- Name: news_id_seq; Type: SEQUENCE; Schema: public; Owner: moringa
--

CREATE SEQUENCE public.news_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_id_seq OWNER TO moringa;

--
-- Name: news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: moringa
--

ALTER SEQUENCE public.news_id_seq OWNED BY public.news.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: moringa
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying,
    "position" character varying,
    role character varying,
    departmentid integer
);


ALTER TABLE public.users OWNER TO moringa;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: moringa
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO moringa;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: moringa
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: departments id; Type: DEFAULT; Schema: public; Owner: moringa
--

ALTER TABLE ONLY public.departments ALTER COLUMN id SET DEFAULT nextval('public.departments_id_seq'::regclass);


--
-- Name: departments_news id; Type: DEFAULT; Schema: public; Owner: moringa
--

ALTER TABLE ONLY public.departments_news ALTER COLUMN id SET DEFAULT nextval('public.departments_news_id_seq'::regclass);


--
-- Name: news id; Type: DEFAULT; Schema: public; Owner: moringa
--

ALTER TABLE ONLY public.news ALTER COLUMN id SET DEFAULT nextval('public.news_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: moringa
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: departments; Type: TABLE DATA; Schema: public; Owner: moringa
--

COPY public.departments (id, name, description, numberofemployees) FROM stdin;
\.


--
-- Data for Name: departments_news; Type: TABLE DATA; Schema: public; Owner: moringa
--

COPY public.departments_news (id, departmentid, newsid) FROM stdin;
\.


--
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: moringa
--

COPY public.news (id, post) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: moringa
--

COPY public.users (id, name, "position", role, departmentid) FROM stdin;
\.


--
-- Name: departments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: moringa
--

SELECT pg_catalog.setval('public.departments_id_seq', 2, true);


--
-- Name: departments_news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: moringa
--

SELECT pg_catalog.setval('public.departments_news_id_seq', 1, true);


--
-- Name: news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: moringa
--

SELECT pg_catalog.setval('public.news_id_seq', 1, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: moringa
--

SELECT pg_catalog.setval('public.users_id_seq', 1, true);


--
-- Name: departments_news departments_news_pkey; Type: CONSTRAINT; Schema: public; Owner: moringa
--

ALTER TABLE ONLY public.departments_news
    ADD CONSTRAINT departments_news_pkey PRIMARY KEY (id);


--
-- Name: departments departments_pkey; Type: CONSTRAINT; Schema: public; Owner: moringa
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (id);


--
-- Name: news news_pkey; Type: CONSTRAINT; Schema: public; Owner: moringa
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: moringa
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

