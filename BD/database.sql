--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

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

--
-- Name: booking_status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.booking_status AS ENUM (
    'подтвержден',
    'завершен',
    'отменен'
);


ALTER TYPE public.booking_status OWNER TO postgres;

--
-- Name: gender; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.gender AS ENUM (
    'мужской',
    'женский'
);


ALTER TYPE public.gender OWNER TO postgres;

--
-- Name: notification_method; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.notification_method AS ENUM (
    'почта',
    'телефон'
);


ALTER TYPE public.notification_method OWNER TO postgres;

--
-- Name: notification_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.notification_type AS ENUM (
    'напоминание о бронировании',
    'напоминание об оплате'
);


ALTER TYPE public.notification_type OWNER TO postgres;

--
-- Name: payment_method; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.payment_method AS ENUM (
    'наличные',
    'банковская карта',
    'онлайн-кошелек'
);


ALTER TYPE public.payment_method OWNER TO postgres;

--
-- Name: payment_status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.payment_status AS ENUM (
    'оплачено',
    'не оплачено'
);


ALTER TYPE public.payment_status OWNER TO postgres;

--
-- Name: update_avg_rating(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_avg_rating() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE buses
    SET avg_rating = COALESCE((
        SELECT ROUND(AVG(rating), 2)
        FROM reviews
        WHERE buses.id = reviews.buses_id
    ), 0)
    WHERE id = NEW.buses_id;

    RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_avg_rating() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: buses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.buses (
    id integer NOT NULL,
    company_id integer NOT NULL,
    model character varying(15) NOT NULL,
    year_of_manufacture integer NOT NULL,
    last_to_date date NOT NULL,
    bus_photos bytea NOT NULL,
    driver_photo bytea NOT NULL,
    driver_license bytea NOT NULL,
    seat_count integer NOT NULL,
    has_tv boolean DEFAULT false,
    has_wifi boolean DEFAULT false,
    has_air_conditioning boolean DEFAULT false,
    has_interior_lighting boolean DEFAULT false,
    has_microphone boolean DEFAULT false,
    has_usb_charger boolean DEFAULT false,
    has_usb_sync boolean DEFAULT false,
    has_accessibility_features boolean DEFAULT false,
    avg_rating numeric(2,1) DEFAULT 0,
    available_start timestamp without time zone,
    available_end timestamp without time zone,
    CONSTRAINT buses_avg_rating_check CHECK (((avg_rating >= (0)::numeric) AND (avg_rating <= (5)::numeric))),
    CONSTRAINT buses_seat_count_check CHECK ((seat_count > 0)),
    CONSTRAINT check_seat_count CHECK ((seat_count > 0))
);


ALTER TABLE public.buses OWNER TO postgres;

--
-- Name: buses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.buses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.buses_id_seq OWNER TO postgres;

--
-- Name: buses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.buses_id_seq OWNED BY public.buses.id;


--
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    first_name character varying(20) NOT NULL,
    last_name character varying(30) NOT NULL,
    middle_name character varying(30),
    birth_date date,
    gender public.gender,
    email character varying(255) NOT NULL,
    phone_number character varying(15) NOT NULL,
    registration_date timestamp without time zone NOT NULL,
    last_login_date timestamp without time zone,
    profile_photo bytea,
    CONSTRAINT users_email_check CHECK (((email)::text ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text)),
    CONSTRAINT users_phone_number_check CHECK (((phone_number)::text ~ '^(\+\d{1,3})?[\d\s\-\(\)]{5,15}$'::text))
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- Name: notifications; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notifications (
    id integer NOT NULL,
    client_id integer NOT NULL,
    notification_type public.notification_type NOT NULL,
    notification_is boolean NOT NULL,
    notification_method public.notification_method NOT NULL
);


ALTER TABLE public.notifications OWNER TO postgres;

--
-- Name: notifications_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.notifications_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.notifications_id_seq OWNER TO postgres;

--
-- Name: notifications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.notifications_id_seq OWNED BY public.notifications.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    client_id integer NOT NULL,
    bus_id integer NOT NULL,
    start_time timestamp without time zone NOT NULL,
    end_time timestamp without time zone NOT NULL,
    start_point character varying(100) NOT NULL,
    end_point character varying(100) NOT NULL,
    passengers_count integer NOT NULL,
    comments text,
    price integer NOT NULL,
    order_status public.booking_status NOT NULL,
    payment_method public.payment_method NOT NULL,
    payment_status public.payment_status NOT NULL,
    order_date timestamp without time zone NOT NULL,
    CONSTRAINT check_passengers_count CHECK ((passengers_count > 0)),
    CONSTRAINT check_price CHECK ((price > 0)),
    CONSTRAINT check_start_end_time CHECK ((start_time <= end_time))
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orders_id_seq OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- Name: rent_conditions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rent_conditions (
    id integer NOT NULL,
    buses_id integer NOT NULL,
    min_hours integer NOT NULL,
    min_cancel_time integer NOT NULL,
    min_rent_time integer NOT NULL,
    price_weekends integer NOT NULL,
    price_weekdays integer NOT NULL,
    CONSTRAINT check_price_weekdays CHECK ((price_weekdays > 0)),
    CONSTRAINT check_price_weekends CHECK ((price_weekends > 0))
);


ALTER TABLE public.rent_conditions OWNER TO postgres;

--
-- Name: rent_conditions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rent_conditions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rent_conditions_id_seq OWNER TO postgres;

--
-- Name: rent_conditions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rent_conditions_id_seq OWNED BY public.rent_conditions.id;


--
-- Name: reviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reviews (
    id integer NOT NULL,
    client_id integer NOT NULL,
    buses_id integer NOT NULL,
    rating integer NOT NULL,
    text character varying(1000),
    creation_date timestamp without time zone NOT NULL,
    answer character varying(1000),
    CONSTRAINT reviews_rating_check CHECK (((rating >= 1) AND (rating <= 5)))
);


ALTER TABLE public.reviews OWNER TO postgres;

--
-- Name: reviews_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reviews_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reviews_id_seq OWNER TO postgres;

--
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;


--
-- Name: transport_companies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transport_companies (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    email character varying(255) NOT NULL,
    phone_number character varying(15) NOT NULL,
    license bytea NOT NULL,
    registration_date timestamp without time zone NOT NULL,
    last_login_date timestamp without time zone,
    profile_photo bytea,
    CONSTRAINT transport_companies_email_check CHECK (((email)::text ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text)),
    CONSTRAINT transport_companies_phone_number_check CHECK (((phone_number)::text ~ '^(\+\d{1,3})?[\d\s\-\(\)]{5,15}$'::text))
);


ALTER TABLE public.transport_companies OWNER TO postgres;

--
-- Name: transport_companies_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transport_companies_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.transport_companies_id_seq OWNER TO postgres;

--
-- Name: transport_companies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transport_companies_id_seq OWNED BY public.transport_companies.id;


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.clients.id;


--
-- Name: buses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buses ALTER COLUMN id SET DEFAULT nextval('public.buses_id_seq'::regclass);


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: notifications id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications ALTER COLUMN id SET DEFAULT nextval('public.notifications_id_seq'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- Name: rent_conditions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rent_conditions ALTER COLUMN id SET DEFAULT nextval('public.rent_conditions_id_seq'::regclass);


--
-- Name: reviews id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);


--
-- Name: transport_companies id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transport_companies ALTER COLUMN id SET DEFAULT nextval('public.transport_companies_id_seq'::regclass);


--
-- Data for Name: buses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.buses (id, company_id, model, year_of_manufacture, last_to_date, bus_photos, driver_photo, driver_license, seat_count, has_tv, has_wifi, has_air_conditioning, has_interior_lighting, has_microphone, has_usb_charger, has_usb_sync, has_accessibility_features, avg_rating, available_start, available_end) FROM stdin;
\.


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (id, first_name, last_name, middle_name, birth_date, gender, email, phone_number, registration_date, last_login_date, profile_photo) FROM stdin;
\.


--
-- Data for Name: notifications; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notifications (id, client_id, notification_type, notification_is, notification_method) FROM stdin;
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, client_id, bus_id, start_time, end_time, start_point, end_point, passengers_count, comments, price, order_status, payment_method, payment_status, order_date) FROM stdin;
\.


--
-- Data for Name: rent_conditions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rent_conditions (id, buses_id, min_hours, min_cancel_time, min_rent_time, price_weekends, price_weekdays) FROM stdin;
\.


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reviews (id, client_id, buses_id, rating, text, creation_date, answer) FROM stdin;
\.


--
-- Data for Name: transport_companies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transport_companies (id, name, email, phone_number, license, registration_date, last_login_date, profile_photo) FROM stdin;
\.


--
-- Name: buses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.buses_id_seq', 1, false);


--
-- Name: notifications_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.notifications_id_seq', 1, false);


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 1, false);


--
-- Name: rent_conditions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rent_conditions_id_seq', 1, false);


--
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reviews_id_seq', 1, false);


--
-- Name: transport_companies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transport_companies_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- Name: buses buses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buses
    ADD CONSTRAINT buses_pkey PRIMARY KEY (id);


--
-- Name: notifications notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: rent_conditions rent_conditions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rent_conditions
    ADD CONSTRAINT rent_conditions_pkey PRIMARY KEY (id);


--
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- Name: transport_companies transport_companies_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transport_companies
    ADD CONSTRAINT transport_companies_email_key UNIQUE (email);


--
-- Name: transport_companies transport_companies_phone_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transport_companies
    ADD CONSTRAINT transport_companies_phone_number_key UNIQUE (phone_number);


--
-- Name: transport_companies transport_companies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transport_companies
    ADD CONSTRAINT transport_companies_pkey PRIMARY KEY (id);


--
-- Name: clients users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: clients users_phone_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT users_phone_number_key UNIQUE (phone_number);


--
-- Name: clients users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: reviews trigger_update_avg_rating; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trigger_update_avg_rating AFTER INSERT OR DELETE OR UPDATE ON public.reviews FOR EACH ROW EXECUTE FUNCTION public.update_avg_rating();


--
-- Name: buses buses_company_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buses
    ADD CONSTRAINT buses_company_id_fkey FOREIGN KEY (company_id) REFERENCES public.transport_companies(id);


--
-- Name: notifications notifications_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_user_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(id);


--
-- Name: orders orders_bus_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_bus_id_fkey FOREIGN KEY (bus_id) REFERENCES public.buses(id);


--
-- Name: orders orders_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_user_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(id);


--
-- Name: rent_conditions rent_conditions_buses_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rent_conditions
    ADD CONSTRAINT rent_conditions_buses_id_fkey FOREIGN KEY (buses_id) REFERENCES public.buses(id);


--
-- Name: reviews reviews_buses_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_buses_id_fkey FOREIGN KEY (buses_id) REFERENCES public.buses(id);


--
-- Name: reviews reviews_users_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_users_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(id);


--
-- PostgreSQL database dump complete
--

