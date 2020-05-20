DROP SCHEMA IF EXISTS `duotexture`;
CREATE SCHEMA `duotexture` ;

CREATE TABLE `duotexture`.`administrators` (
	`administratorId` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) UNIQUE NOT NULL,
    `username` VARCHAR(45) UNIQUE NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`administratorId`)
);

INSERT INTO duotexture.administrators(`email`, `username`, `password`) VALUES('renfred.19@ichat.sp.edu.sg', 'renfreddd', 'password');
INSERT INTO duotexture.administrators(`email`, `username`, `password`) VALUES('kelyn.19@ichat.sp.edu.sg', 'kelynwong', 'password');
INSERT INTO duotexture.administrators(`email`, `username`, `password`) VALUES('teacher@ichat.sp.edu.sg', 'teacher', 'password');

CREATE TABLE `duotexture`.`members` (
  `memberId` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) UNIQUE NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`memberId`));
  
INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer1@ichat.sp.edu.sg', 'customer1', 'password', 'customer', '1', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer2@ichat.sp.edu.sg', 'customer2', 'password', 'customer', '2', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer3@ichat.sp.edu.sg', 'customer3', 'password', 'customer', '3', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer4@ichat.sp.edu.sg', 'customer4', 'password', 'customer', '4', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer5@ichat.sp.edu.sg', 'customer5', 'password', 'customer', '5', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer6@ichat.sp.edu.sg', 'customer6', 'password', 'customer', '6', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer7@ichat.sp.edu.sg', 'customer7', 'password', 'customer', '7', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer8@ichat.sp.edu.sg', 'customer8', 'password', 'customer', '8', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer9@ichat.sp.edu.sg', 'customer9', 'password', 'customer', '9', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');
INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES('customer10@ichat.sp.edu.sg', 'customer10', 'password', 'customer', '10', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '12345');

CREATE TABLE `duotexture`.`categories` (
  `categoryId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` LONGTEXT NOT NULL,
  `image` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`categoryId`));
  
INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES('Men', 'IN STYLE - The Casual Classics.<br>Vintage styles with a modern twist to match everything you have.', './assets/images/image1.jpg');
INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES('Women', "MOTHERS' DAY SPECIAL<br>Extra 30% off! Use code `30MOM` to get your mother a gift! Promotion ends on 30 May 2020.", './assets/images/image1.jpg');
INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES('Kids', 'Kids sportswear up to 40% off.<br>Cruise through the week with our fashionable picks at discounted price.', './assets/images/image1.jpg');

CREATE TABLE `duotexture`.`products` (
  `productId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) UNIQUE NOT NULL,
  `description` LONGTEXT NOT NULL,
  `cost_price` DOUBLE NOT NULL,
  `retail_price` DOUBLE NOT NULL,
  `quantity` INT NOT NULL,
  `categoryId` INT NOT NULL,
  `image` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`productId`),
  FOREIGN KEY (`categoryId`) REFERENCES `duotexture`.`categories` (`categoryId`) ON DELETE CASCADE);

INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture V-neck Shirt', 'Duo-Texture produced.', '10', '32.00', '180', '1', './assets/images/image4.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Semi Wide Slacks', 'Duo-Texture produced.', '15', '49.90', '510', '1', './assets/images/image5.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Leather Loafer', 'Duo-Texture produced.', '7.20', '25.80', '520', '1', './assets/images/image6.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Rider Leather Jacket', 'Duo-Texture produced.', '31', '56.00', '820', '1', './assets/images/image7.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Bledy Paisley Shirt', 'Duo-Texture produced.', '9.90', '23.90', '160', '1', './assets/images/image8.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Denim Pants', 'Duo-Texture produced.', '7.20', '22.80', '290', '1', './assets/images/image9.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Circle Slick necklace', 'Duo-Texture produced.', '3.40', '11.49', '840', '1', './assets/images/image10.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Denim Jacket', 'Duo-Texture produced.', '37', '63.95', '470', '1', './assets/images/image11.jpg');


INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Lace Short Sleeve Shirt', 'Duo-Texture produced.', '24', '59.40', '450', '2', './assets/images/image12.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Collar Chiffon Shirt', 'Duo-Texture produced.', '18', '47.20', '370', '2', './assets/images/image13.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture High Waist Skirt', 'Duo-Texture produced.', '20', '52.75', '290', '2', './assets/images/image14.jpg');


INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Short Sleeve T-shirt', 'Duo-Texture produced.', '3.50', '10.50', '1000', '3', './assets/images/image15.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Harem Pants', 'Duo-Texture produced.', '2', '7.99', '130', '3', './assets/images/image16.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Sleeveless T-shirt', 'Duo-Texture produced.', '6.70', '22.80', '490', '3', './assets/images/image17.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Flower Floral Leggings', 'Duo-Texture produced.', '3.50', '10.50', '740', '3', './assets/images/image18.jpg');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Cotton T-shirt', 'Duo-Texture produced.', '3', '7.99', '280', '3', './assets/images/image19.jpg'); 
