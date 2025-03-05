--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.4

-- Started on 2025-03-05 15:57:30

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
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4117 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 225 (class 1259 OID 16524)
-- Name: m_beneficiaries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_beneficiaries (
    ccno integer NOT NULL,
    chss_char character(1) NOT NULL,
    rela_code integer,
    bene_title text,
    active_from date,
    bene_name text,
    bene_sex character(1),
    bene_dob date,
    bene_aadhaar character(12),
    bene_bld_grp character(3),
    bene_rh_fact character(1),
    active_to date,
    active_stat integer,
    insert_date date
);


ALTER TABLE public.m_beneficiaries OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16431)
-- Name: m_claim; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_claim (
    pk_claim integer NOT NULL,
    emp_title text,
    emp_name text,
    emp_desg text,
    emp_divn text,
    emp_secn text,
    emp_lab text,
    emp_group text,
    emp_ccno integer,
    bene_name text,
    chss_char text,
    bene_rela text,
    amt_claimed integer,
    amt_approved integer,
    insert_date timestamp without time zone,
    insert_ccno integer,
    insert_userid text,
    insert_ip text,
    curr_stat text,
    doct_ccno integer,
    file_id bigint
);


ALTER TABLE public.m_claim OWNER TO postgres;

--
-- TOC entry 4118 (class 0 OID 0)
-- Dependencies: 218
-- Name: TABLE m_claim; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.m_claim IS 'Table for medical reimbersment records';


--
-- TOC entry 224 (class 1259 OID 16519)
-- Name: m_employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_employee (
    e_ccno integer NOT NULL,
    e_title text,
    e_name text,
    e_desg_code integer,
    e_desg_desc text,
    e_divn_code character(5),
    e_divn_desc text,
    e_secn_code character(5),
    e_secn_desc text,
    e_lab_code character(5),
    e_lab_desc text,
    e_grp_code character(5),
    e_grp_desc text,
    insert_date date,
    e_email text NOT NULL,
    e_mobile_no integer NOT NULL
);


ALTER TABLE public.m_employee OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16468)
-- Name: operation_agency; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.operation_agency (
    agen_code text,
    insert_date date,
    act_inact character(1),
    from_date date,
    to_date date,
    oper_code integer
);


ALTER TABLE public.operation_agency OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16458)
-- Name: r_agencies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_agencies (
    agen_code character(3) NOT NULL,
    agen_order integer,
    act_inact character(1),
    agen_name text,
    agen_ccno integer,
    from_date date,
    to_date date,
    insert_date date
);


ALTER TABLE public.r_agencies OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16423)
-- Name: r_operations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_operations (
    oper_code integer NOT NULL,
    oper_name text,
    oper_desc text,
    oper_active character(1),
    order_by integer
);


ALTER TABLE public.r_operations OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16441)
-- Name: t_bills; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_bills (
    pk_claim integer,
    bill_date date,
    store_name text,
    insert_date date
);


ALTER TABLE public.t_bills OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16436)
-- Name: t_pres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_pres (
    pk_claim integer,
    pres_date date,
    pres_agency text,
    pres_doct text
);


ALTER TABLE public.t_pres OWNER TO postgres;

--
-- TOC entry 4119 (class 0 OID 0)
-- Dependencies: 219
-- Name: TABLE t_pres; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.t_pres IS 'Prescription Details';


--
-- TOC entry 222 (class 1259 OID 16463)
-- Name: t_transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_transactions (
    pk_claim integer,
    agen_code text,
    oper_code integer,
    insert_date date,
    insert_by_ccno integer,
    insert_by_userid text
);


ALTER TABLE public.t_transactions OWNER TO postgres;

--
-- TOC entry 3956 (class 2606 OID 16532)
-- Name: m_beneficiaries m_beneficiaries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_beneficiaries
    ADD CONSTRAINT m_beneficiaries_pkey PRIMARY KEY (ccno, chss_char);


--
-- TOC entry 3950 (class 2606 OID 16474)
-- Name: m_claim m_claim_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_claim
    ADD CONSTRAINT m_claim_pkey PRIMARY KEY (pk_claim);


--
-- TOC entry 3954 (class 2606 OID 16530)
-- Name: m_employee m_employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_employee
    ADD CONSTRAINT m_employee_pkey PRIMARY KEY (e_ccno);


--
-- TOC entry 3952 (class 2606 OID 16481)
-- Name: r_agencies operating_agencies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_agencies
    ADD CONSTRAINT operating_agencies_pkey PRIMARY KEY (agen_code);


--
-- TOC entry 3948 (class 2606 OID 16483)
-- Name: r_operations r_operations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_operations
    ADD CONSTRAINT r_operations_pkey PRIMARY KEY (oper_code);


--
-- TOC entry 3964 (class 2606 OID 16489)
-- Name: operation_agency fk_agen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.operation_agency
    ADD CONSTRAINT fk_agen FOREIGN KEY (agen_code) REFERENCES public.r_agencies(agen_code) NOT VALID;


--
-- TOC entry 3961 (class 2606 OID 16499)
-- Name: t_transactions fk_agen_done; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transactions
    ADD CONSTRAINT fk_agen_done FOREIGN KEY (agen_code) REFERENCES public.r_agencies(agen_code) NOT VALID;


--
-- TOC entry 3957 (class 2606 OID 16538)
-- Name: m_claim fk_bene; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_claim
    ADD CONSTRAINT fk_bene FOREIGN KEY (emp_ccno, chss_char) REFERENCES public.m_beneficiaries(ccno, chss_char) NOT VALID;


--
-- TOC entry 3966 (class 2606 OID 16543)
-- Name: m_beneficiaries fk_emp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_beneficiaries
    ADD CONSTRAINT fk_emp FOREIGN KEY (ccno) REFERENCES public.m_employee(e_ccno) NOT VALID;


--
-- TOC entry 3958 (class 2606 OID 16533)
-- Name: m_claim fk_empl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_claim
    ADD CONSTRAINT fk_empl FOREIGN KEY (emp_ccno) REFERENCES public.m_employee(e_ccno) NOT VALID;


--
-- TOC entry 3965 (class 2606 OID 16484)
-- Name: operation_agency fk_oper; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.operation_agency
    ADD CONSTRAINT fk_oper FOREIGN KEY (oper_code) REFERENCES public.r_operations(oper_code) NOT VALID;


--
-- TOC entry 3962 (class 2606 OID 16494)
-- Name: t_transactions fk_oper_done; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transactions
    ADD CONSTRAINT fk_oper_done FOREIGN KEY (oper_code) REFERENCES public.r_operations(oper_code) NOT VALID;


--
-- TOC entry 3963 (class 2606 OID 16504)
-- Name: t_transactions fk_pk_claim; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transactions
    ADD CONSTRAINT fk_pk_claim FOREIGN KEY (pk_claim) REFERENCES public.m_claim(pk_claim) NOT VALID;


--
-- TOC entry 3960 (class 2606 OID 16514)
-- Name: t_bills fk_pk_claim_bills; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_bills
    ADD CONSTRAINT fk_pk_claim_bills FOREIGN KEY (pk_claim) REFERENCES public.m_claim(pk_claim) NOT VALID;


--
-- TOC entry 3959 (class 2606 OID 16509)
-- Name: t_pres fk_pk_claim_pres; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_pres
    ADD CONSTRAINT fk_pk_claim_pres FOREIGN KEY (pk_claim) REFERENCES public.m_claim(pk_claim) NOT VALID;


-- Completed on 2025-03-05 15:57:30

--
-- PostgreSQL database dump complete
--

