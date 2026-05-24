--
-- PostgreSQL database dump
--

\restrict KkaHVKvr5mSzLJKdE48RB0XId1NJkO3baMJpirpisYaZ3a0f0fziU7ZazulMcp2

-- Dumped from database version 18.4
-- Dumped by pg_dump version 18.4

-- Started on 2026-05-24 16:26:37 -03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 224 (class 1259 OID 16643)
-- Name: compras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.compras (
    id integer NOT NULL,
    usuario_id integer NOT NULL,
    jogo_id integer NOT NULL,
    quantidade integer NOT NULL
);


ALTER TABLE public.compras OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16642)
-- Name: compras_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compras_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.compras_id_seq OWNER TO postgres;

--
-- TOC entry 3510 (class 0 OID 0)
-- Dependencies: 223
-- Name: compras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compras_id_seq OWNED BY public.compras.id;


--
-- TOC entry 222 (class 1259 OID 16598)
-- Name: jogos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jogos (
    id integer NOT NULL,
    nome character varying(200) NOT NULL,
    preco double precision NOT NULL,
    imagem bytea NOT NULL
);


ALTER TABLE public.jogos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16597)
-- Name: jogos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jogos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jogos_id_seq OWNER TO postgres;

--
-- TOC entry 3511 (class 0 OID 0)
-- Dependencies: 221
-- Name: jogos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jogos_id_seq OWNED BY public.jogos.id;


--
-- TOC entry 220 (class 1259 OID 16583)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id integer NOT NULL,
    nome character varying(200) DEFAULT 'Usuario'::character varying,
    email character varying(200) NOT NULL,
    senha character varying(200) NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16582)
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuarios_id_seq OWNER TO postgres;

--
-- TOC entry 3512 (class 0 OID 0)
-- Dependencies: 219
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- TOC entry 3341 (class 2604 OID 16646)
-- Name: compras id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras ALTER COLUMN id SET DEFAULT nextval('public.compras_id_seq'::regclass);


--
-- TOC entry 3340 (class 2604 OID 16601)
-- Name: jogos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogos ALTER COLUMN id SET DEFAULT nextval('public.jogos_id_seq'::regclass);


--
-- TOC entry 3338 (class 2604 OID 16586)
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- TOC entry 3504 (class 0 OID 16643)
-- Dependencies: 224
-- Data for Name: compras; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.compras (id, usuario_id, jogo_id, quantidade) FROM stdin;
\.


--
-- TOC entry 3502 (class 0 OID 16598)
-- Dependencies: 222
-- Data for Name: jogos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jogos (id, nome, preco, imagem) FROM stdin;
\.


--
-- TOC entry 3500 (class 0 OID 16583)
-- Dependencies: 220
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id, nome, email, senha) FROM stdin;
1	Renan	renanrobrox11@gmail.com	277025254caef1073378c613c0d0bace
\.


--
-- TOC entry 3513 (class 0 OID 0)
-- Dependencies: 223
-- Name: compras_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compras_id_seq', 1, false);


--
-- TOC entry 3514 (class 0 OID 0)
-- Dependencies: 221
-- Name: jogos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jogos_id_seq', 1, false);


--
-- TOC entry 3515 (class 0 OID 0)
-- Dependencies: 219
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_seq', 1, true);


--
-- TOC entry 3349 (class 2606 OID 16652)
-- Name: compras compras_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT compras_pkey PRIMARY KEY (id);


--
-- TOC entry 3347 (class 2606 OID 16609)
-- Name: jogos jogos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogos
    ADD CONSTRAINT jogos_pkey PRIMARY KEY (id);


--
-- TOC entry 3343 (class 2606 OID 16596)
-- Name: usuarios usuarios_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_email_key UNIQUE (email);


--
-- TOC entry 3345 (class 2606 OID 16594)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


--
-- TOC entry 3350 (class 2606 OID 16658)
-- Name: compras fk_jogos; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT fk_jogos FOREIGN KEY (jogo_id) REFERENCES public.jogos(id);


--
-- TOC entry 3351 (class 2606 OID 16653)
-- Name: compras fk_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);


-- Completed on 2026-05-24 16:26:37 -03

--
-- PostgreSQL database dump complete
--

\unrestrict KkaHVKvr5mSzLJKdE48RB0XId1NJkO3baMJpirpisYaZ3a0f0fziU7ZazulMcp2

