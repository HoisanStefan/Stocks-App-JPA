-- DROP TABLE IF EXISTS brands;
-- DROP TABLE IF EXISTS providers;
-- DROP TABLE IF EXISTS offers;
-- DROP TABLE IF EXISTS products;
-- DROP TABLE IF EXISTS resupplies;
-- DROP TABLE IF EXISTS sales;

CREATE TABLE IF NOT EXISTS BRANDS (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS PROVIDERS (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(100),
    phone VARCHAR(100),
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS OFFERS (
    id INT NOT NULL AUTO_INCREMENT,
    percentage INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS PRODUCTS (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    price_provider DOUBLE NOT NULL,
    provider_id INT NOT NULL,
    brand_id INT NOT NULL,
    offer_id INT,
    PRIMARY KEY (id),
    UNIQUE (name),
    foreign key (provider_id) references PROVIDERS(id),
    foreign key (brand_id) references BRANDS(id),
    foreign key (offer_id) references OFFERS(id)
);

CREATE TABLE IF NOT EXISTS RESUPPLIES (
    id INT NOT NULL AUTO_INCREMENT,
    quantity INT NOT NULL,
    provider_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (id),
    foreign key (provider_id) references PROVIDERS(id),
    foreign key (product_id) references PRODUCTS(id)
);

CREATE TABLE IF NOT EXISTS SALES (
    id INT NOT NULL AUTO_INCREMENT,
    quantity INT NOT NULL,
    total_value INT,
    price_per_unit INT,
    product_id INT NOT NULL,
    PRIMARY KEY (id),
    foreign key (product_id) references PRODUCTS(id)
);
