DROP SCHEMA IF EXISTS `duotexture`;
CREATE SCHEMA `duotexture` ;

CREATE TABLE `duotexture`.`administrator` (
	`administratorID` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `username` VARCHAR(45) UNIQUE NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`administratorID`)
);

INSERT INTO duotexture.administrator(`email`, `username`, `password`) VALUES('renfred.19@ichat.sp.edu.sg', 'renfreddd', 'password');
INSERT INTO duotexture.administrator(`email`, `username`, `password`) VALUES('kelyn.19@ichat.sp.edu.sg', 'kelynwong', 'password');

CREATE TABLE `duotexture`.`member` (
  `memberID` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`memberID`));
  
INSERT INTO duotexture.member(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer1@ichat.sp.edu.sg', 'customer1', 'password', 'customer', '1', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.member(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer2@ichat.sp.edu.sg', 'customer2', 'password', 'customer', '2', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');

CREATE TABLE `duotexture`.`category` (
  `categoryID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` LONGTEXT NOT NULL,
  `image` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`categoryID`));
  
INSERT INTO duotexture.category(`name`, `description`, `image`) VALUES('Shirts', 'This are just some simple shirts produced by duo-texture.', '/images/category_1.png');
INSERT INTO duotexture.category(`name`, `description`, `image`) VALUES('Pants', 'This are just some simple pants produced by duo-texture.', '/images/category_2.png');
INSERT INTO duotexture.category(`name`, `description`, `image`) VALUES('Accessories', 'This are just some simple accessories produced by duo-texture.', '/images/category_3.png');

DROP TABLE duotexture.product;
CREATE TABLE `duotexture`.`product` (
  `productID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` LONGTEXT NOT NULL,
  `cost_price` DOUBLE NOT NULL,
  `retail_price` DOUBLE NOT NULL,
  `quantity` INT NOT NULL,
  `categoryID` INT NOT NULL,
  `image` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`productID`),
  FOREIGN KEY (`categoryID`) REFERENCES `duotexture`.`category` (`categoryID`) ON DELETE CASCADE);
  
SELECT * FROM duotexture.product;
INSERT INTO duotexture.product(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryID`, `image`) VALUES('Duo Texure Slim Cut Shirt', 'Duo-Texture produced. Slim cut shirts are normally worn on summers as they are breezy and thin.', '10', '32.00', '180', '1', '/images/product_1.png');
INSERT INTO duotexture.product(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryID`, `image`) VALUES('Duo Texure V-neck Shirt', 'Duo-Texture produced.', '15', '49.90', '510', '1', '/images/product_2.png');
INSERT INTO duotexture.product(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryID`, `image`) VALUES('Duo Texure Aloha Shirt', 'Duo-Texture produced.', '7.20', '25.80', '520', '1', '/images/product_3.png');
INSERT INTO duotexture.product(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryID`, `image`) VALUES('Duo Texure Denim Jeans', 'Duo-Texture produced.', '24', '59.40', '450', '2', '/images/product_4.png');
INSERT INTO duotexture.product(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryID`, `image`) VALUES('Duo Texure Khakis', 'Duo-Texture produced.', '18', '47.20', '370', '2', '/images/product_5.png');
INSERT INTO duotexture.product(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryID`, `image`) VALUES('Duo Texure Pleated Pants', 'Duo-Texture produced.', '20', '52.75', '290', '2', '/images/product_6.png');
INSERT INTO duotexture.product(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryID`, `image`) VALUES('Duo Texure Sunglasses', 'Duo-Texture produced.', '3.50', '10.50', '1000', '3', '/images/product_7.png');
INSERT INTO duotexture.product(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryID`, `image`) VALUES('Duo Texure Choker Necklace', 'Duo-Texture produced.', '2', '7.99', '130', '3', '/images/product_8.png');
INSERT INTO duotexture.product(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryID`, `image`) VALUES('Duo Texure Fedora', 'Duo-Texture produced.', '6.70', '22.80', '490', '3', '/images/product_9.png');