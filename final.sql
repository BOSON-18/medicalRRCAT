--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.2

-- Started on 2025-03-16 14:48:24

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
-- TOC entry 221 (class 1259 OID 16581)
-- Name: c_bill; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.c_bill (
    c_id integer NOT NULL,
    b_store_name character varying(50) NOT NULL,
    bill_date date NOT NULL,
    b_insert_date date
);


ALTER TABLE public.c_bill OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16586)
-- Name: c_prescription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.c_prescription (
    c_id integer NOT NULL,
    p_doctor integer NOT NULL,
    p_doctor_name character varying(50),
    p_date date NOT NULL
);


ALTER TABLE public.c_prescription OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16605)
-- Name: c_stage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.c_stage (
    dr_ccno integer NOT NULL,
    op_code integer NOT NULL,
    op_name character varying(15),
    insert_date date,
    insert_ccno integer,
    insert_userid character varying(15),
    c_stage_id integer NOT NULL,
    c_id integer NOT NULL
);


ALTER TABLE public.c_stage OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16498)
-- Name: m_beneficiaries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_beneficiaries (
    e_ccno integer NOT NULL,
    b_chss_char character(1) NOT NULL,
    b_relation_code integer,
    b_title character varying(20),
    b_name character varying(50) NOT NULL,
    b_sex character(1),
    b_dob date,
    b_aadhar_no character varying(12),
    b_blood_group character varying(3),
    "b_rh_factor " character(1),
    b_start_date date,
    b_end_date date,
    b_is_active boolean,
    b_insert_date date
);


ALTER TABLE public.m_beneficiaries OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16506)
-- Name: m_claim; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_claim (
    c_id integer NOT NULL,
    e_ccno integer NOT NULL,
    e_title character varying(50),
    e_name character varying(50),
    e_desg_code integer,
    e_divn_code character varying(5),
    e_secn_code character varying(5),
    e_lab_code character varying(5),
    e_grp_code character varying(5),
    b_chss_char character(1) NOT NULL,
    b_name character varying(50) NOT NULL,
    b_relation_code integer,
    c_amt_claimed integer NOT NULL,
    c_amt_approved integer DEFAULT 0,
    c_dr_ccno integer NOT NULL,
    c_doc_mongo_id character varying(50) NOT NULL,
    c_doc_hash_code text NOT NULL,
    c_insert_userid character varying(20) NOT NULL,
    c_insert_ccno integer,
    c_insert_ip text,
    c_insert_date date,
    c_curr_stage character varying(10),
    c_curr_stage_code integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.m_claim OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16505)
-- Name: m_claim_c_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.m_claim_c_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.m_claim_c_id_seq OWNER TO postgres;

--
-- TOC entry 4936 (class 0 OID 0)
-- Dependencies: 219
-- Name: m_claim_c_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.m_claim_c_id_seq OWNED BY public.m_claim.c_id;


--
-- TOC entry 223 (class 1259 OID 16591)
-- Name: m_doctor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_doctor (
    dr_ccno integer NOT NULL,
    dr_name character varying(50),
    dr_incharge boolean NOT NULL,
    start_date date,
    end_date date,
    is_active boolean,
    insert_date date
);


ALTER TABLE public.m_doctor OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16491)
-- Name: m_employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_employee (
    e_ccno integer NOT NULL,
    e_title character varying(50) NOT NULL,
    e_name character varying(50) NOT NULL,
    e_email character varying(50) NOT NULL,
    e_mobile_no integer NOT NULL,
    e_desg_code integer NOT NULL,
    e_desg_desc character varying(100),
    e_divn_code character varying(5),
    e_divn_desc character varying(100),
    e_secn_code character varying(5),
    e_secn_desc character varying(100),
    e_lab_code character varying(5),
    e_lab_desc character varying(100),
    e_grp_code character varying(5),
    e_grp_desc character varying(100),
    insert_date date
);


ALTER TABLE public.m_employee OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16596)
-- Name: m_operation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_operation (
    op_code integer NOT NULL,
    op_name character varying(15) NOT NULL,
    op_desc character varying(50),
    is_active boolean DEFAULT true
);


ALTER TABLE public.m_operation OWNER TO postgres;

--
-- TOC entry 4762 (class 2604 OID 16509)
-- Name: m_claim c_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_claim ALTER COLUMN c_id SET DEFAULT nextval('public.m_claim_c_id_seq'::regclass);


--
-- TOC entry 4773 (class 2606 OID 16585)
-- Name: c_bill c_bill_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.c_bill
    ADD CONSTRAINT c_bill_pkey PRIMARY KEY (c_id);


--
-- TOC entry 4775 (class 2606 OID 16590)
-- Name: c_prescription c_prescription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.c_prescription
    ADD CONSTRAINT c_prescription_pkey PRIMARY KEY (c_id);


--
-- TOC entry 4781 (class 2606 OID 16661)
-- Name: c_stage c_stage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.c_stage
    ADD CONSTRAINT c_stage_pkey PRIMARY KEY (c_stage_id);


--
-- TOC entry 4769 (class 2606 OID 16504)
-- Name: m_beneficiaries m_beneficiaries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_beneficiaries
    ADD CONSTRAINT m_beneficiaries_pkey PRIMARY KEY (e_ccno, b_chss_char);


--
-- TOC entry 4771 (class 2606 OID 16515)
-- Name: m_claim m_claim_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_claim
    ADD CONSTRAINT m_claim_pkey PRIMARY KEY (c_id);


--
-- TOC entry 4777 (class 2606 OID 16595)
-- Name: m_doctor m_doctor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_doctor
    ADD CONSTRAINT m_doctor_pkey PRIMARY KEY (dr_ccno);


--
-- TOC entry 4767 (class 2606 OID 16497)
-- Name: m_employee m_employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_employee
    ADD CONSTRAINT m_employee_pkey PRIMARY KEY (e_ccno);


--
-- TOC entry 4779 (class 2606 OID 16601)
-- Name: m_operation m_operation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_operation
    ADD CONSTRAINT m_operation_pkey PRIMARY KEY (op_code);


--
-- TOC entry 4782 (class 2606 OID 16610)
-- Name: m_beneficiaries fk_bene_emp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_beneficiaries
    ADD CONSTRAINT fk_bene_emp FOREIGN KEY (e_ccno) REFERENCES public.m_employee(e_ccno) NOT VALID;


--
-- TOC entry 4783 (class 2606 OID 16635)
-- Name: m_claim fk_claim_dr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_claim
    ADD CONSTRAINT fk_claim_dr FOREIGN KEY (c_dr_ccno) REFERENCES public.m_doctor(dr_ccno) NOT VALID;


--
-- TOC entry 4784 (class 2606 OID 16615)
-- Name: m_claim fk_claim_emp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_claim
    ADD CONSTRAINT fk_claim_emp FOREIGN KEY (e_ccno) REFERENCES public.m_employee(e_ccno) NOT VALID;


--
-- TOC entry 4785 (class 2606 OID 16655)
-- Name: m_claim fk_claim_pres; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_claim
    ADD CONSTRAINT fk_claim_pres FOREIGN KEY (c_id) REFERENCES public.c_prescription(c_id) NOT VALID;


-- Completed on 2025-03-16 14:48:24

--
-- PostgreSQL database dump complete
--

