insert into customer(customer_id, customer_name) values(1, 'Barny');
insert into customer(customer_id, customer_name) values(2, 'Wilma');
insert into customer(customer_id, customer_name) values(3, 'Bam-Bam');
insert into customer(customer_id, customer_name) values(4, 'Betty');

insert into phone_number(customer_id, number, is_active) values (1, '01234 56789', false);
insert into phone_number(customer_id, number, is_active) values (1, '09876 54321', false);
insert into phone_number(customer_id, number, is_active) values (2, '07654 12345', false);
insert into phone_number(customer_id, number, is_active) values (3, '07846 98743', false);