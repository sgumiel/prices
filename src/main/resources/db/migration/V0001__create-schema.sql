CREATE TABLE PRICES (
   ID IDENTITY,
   BRAND_ID INTEGER,
   START_DATE TIMESTAMP,
   END_DATE TIMESTAMP,
   PRICE_LIST INTEGER,
   PRODUCT_ID INTEGER,
   PRIORITY INTEGER,
   PRICE DOUBLE,
   CURR VARCHAR(10)
);