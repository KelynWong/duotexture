DROP SCHEMA IF EXISTS `duotexture`;
CREATE SCHEMA `duotexture` ;

CREATE TABLE `duotexture`.`administrator` (
	`administrator_id` INT NOT NULL AUTO_INCREMENT,
    `administrator_email` VARCHAR(255) NOT NULL,
    `username` VARCHAR(45) UNIQUE NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`administrator_id`)
);

INSERT INTO duotexture.administrator(`administrator_email`, `username`, `password`) VALUES('renfred.19@ichat.sp.edu.sg', 'renfreddd', 'password');
INSERT INTO duotexture.administrator(`administrator_email`, `username`, `password`) VALUES('kelyn.19@ichat.sp.edu.sg', 'kelynwong', 'password');

CREATE TABLE `duotexture`.`customers` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_email` VARCHAR(255) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customer_id`));
  
INSERT INTO duotexture.customers(`customer_email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer1@ichat.sp.edu.sg', 'customer1', 'password', 'customer', '1', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.customers(`customer_email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer2@ichat.sp.edu.sg', 'customer2', 'password', 'customer', '2', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');

CREATE TABLE `duotexture`.`categories` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  `category_desc` LONGTEXT NOT NULL,
  `category_image` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`category_id`));
  
INSERT INTO duotexture.categories(`category_name`, `category_desc`, `category_image`) VALUES('Shirts', 'This are just some simple shirts produced by duo-texture.', '/images/category_1.png');

CREATE TABLE `duotexture`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(45) NOT NULL,
  `product_desc` LONGTEXT NOT NULL,
  `product_image` VARCHAR(45) NULL,
  `price` DOUBLE NOT NULL,
  `stock` INT NOT NULL,
  `fk_category_id` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  FOREIGN KEY (`fk_category_id`) REFERENCES `duotexture`.`categories` (`category_id`) ON DELETE CASCADE);

INSERT INTO duotexture.products(`product_name`, `product_desc`, `product_image`,`price`, `stock`, `fk_category_id`) VALUES('Duo Texure Slim Cut Shirt', 'Duo-Texture produced. Slim cut shirts are normally worn on summers as they are breezy and thin.', '/images/product_1.png', '10', '10', '1');
