-- Reset `duotexture` schema
DROP SCHEMA IF EXISTS `duotexture`;
CREATE SCHEMA `duotexture` ;

-- Users Table
CREATE TABLE `duotexture`.`users` (
	`userId` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) UNIQUE NOT NULL,
    `username` VARCHAR(45) UNIQUE NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `userRole` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`userId`)
);

INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('renfred.19@ichat.sp.edu.sg', 'renfreddd', 'password', 'Admin');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('kelyn.19@ichat.sp.edu.sg', 'kelynwong', 'password', 'Admin');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('teacher@ichat.sp.edu.sg', 'teacher', 'password', 'Admin');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer1@ichat.sp.edu.sg', 'customer1', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer2@ichat.sp.edu.sg', 'customer2', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer3@ichat.sp.edu.sg', 'customer3', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer4@ichat.sp.edu.sg', 'customer4', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer5@ichat.sp.edu.sg', 'customer5', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer6@ichat.sp.edu.sg', 'customer6', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer7@ichat.sp.edu.sg', 'customer7', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer8@ichat.sp.edu.sg', 'customer8', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer9@ichat.sp.edu.sg', 'customer9', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer10@ichat.sp.edu.sg', 'customer10', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer11@ichat.sp.edu.sg', 'customer11', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer12@ichat.sp.edu.sg', 'customer12', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer13@ichat.sp.edu.sg', 'customer13', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer14@ichat.sp.edu.sg', 'customer14', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer15@ichat.sp.edu.sg', 'customer15', 'password', 'Member');
INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES('customer16@ichat.sp.edu.sg', 'customer16', 'password', 'Member');

-- Members Table
CREATE TABLE `duotexture`.`members` (
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `userId` INT NOT NULL,
  FOREIGN KEY (`userId`) REFERENCES `duotexture`.`users` (`userId`) ON DELETE CASCADE);
  
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '1', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '123456', 4);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '2', 'United States', 'Los Angelus, Unit #21-222', '423233', 5);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '3', 'Canada', 'Toronto 21 House 221 #01-234', '544542', 6);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '4', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '123456', 7);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '5', 'United States', 'Los Angelus, Unit #21-222', '423233', 8);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '6', 'Canada', 'Toronto 21 House 221 #01-234', '544542', 9);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '7', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '123456', 10);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '8', 'United States', 'Los Angelus, Unit #21-222', '423233', 11);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '9', 'Canada', 'Toronto 21 House 221 #01-234', '544542', 12);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '10', 'Singapore', 'Blk 123 Tampines Street 12 #01-234', '123456', 13);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '11', 'United States', 'Los Angelus, Unit #21-222', '423233', 14);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '12', 'Canada', 'Toronto 21 House 221 #01-234', '544542', 15);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '13', 'Canada', 'Toronto 21 House 221 #01-234', '544542', 16);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '14', 'Canada', 'Toronto 21 House 221 #01-234', '544542', 17);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '15', 'Canada', 'Toronto 21 House 221 #01-234', '544542', 18);
INSERT INTO duotexture.members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES('customer', '16', 'Canada', 'Toronto 21 House 221 #01-234', '544542', 19);

-- Categories Table
CREATE TABLE `duotexture`.`categories` (
  `categoryId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) UNIQUE NOT NULL,
  `description` LONGTEXT NOT NULL,
  `image` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`categoryId`));
  
INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES('Men', 'IN STYLE - The Casual Classics.<br>Vintage styles with a modern twist to match everything you have.', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image1.jpg20200810_184859');
INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES('Women', "MOTHERS' DAY SPECIAL<br>Extra 30% off! Use code `30MOM` to get your mother a gift! Promotion ends on 30 May 2020.", 'https://jad-bucket.s3.us-east-2.amazonaws.com/image2.jpg20200810_184909');
INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES('Kids', 'Kids sportswear up to 40% off.<br>Cruise through the week with our fashionable picks at discounted price.', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image3.jpg20200810_184918');

-- Products Table
CREATE TABLE `duotexture`.`products` (
  `productId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) UNIQUE NOT NULL,
  `description` LONGTEXT NOT NULL,
  `cost_price` DOUBLE NOT NULL,
  `retail_price` DOUBLE NOT NULL,
  `quantity` INT NOT NULL,
  `categoryId` INT NOT NULL,
  `image` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`productId`),
  FOREIGN KEY (`categoryId`) REFERENCES `duotexture`.`categories` (`categoryId`) ON DELETE CASCADE);

INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture V-neck Shirt', 'Duo-Texture produced.', '10', '32.00', '180', '1', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image4.jpg20200810_184926');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Semi Wide Slacks', 'Duo-Texture produced.', '15', '49.90', '510', '1', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image5.jpg20200810_184934');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Leather Loafer', 'Duo-Texture produced.', '7.20', '25.80', '520', '1', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image6.jpg20200810_184951');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Rider Leather Jacket', 'Duo-Texture produced.', '31', '56.00', '820', '1', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image7.jpg20200810_185015');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Bledy Paisley Shirt', 'Duo-Texture produced.', '9.90', '23.90', '160', '1', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image8.jpg20200810_185025');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Denim Pants', 'Duo-Texture produced.', '7.20', '22.80', '290', '1', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image9.jpg20200810_185033');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Circle Slick Necklace', 'Duo-Texture produced.', '3.40', '11.49', '840', '1', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image10.jpg20200810_185050');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Denim Jacket', 'Duo-Texture produced.', '37', '63.95', '470', '1', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image11.jpg20200810_185105');


INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Lace Short Sleeve Shirt', 'Duo-Texture produced.', '24', '59.40', '450', '2', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image12.jpg20200810_185116');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Collar Chiffon Shirt', 'Duo-Texture produced.', '18', '47.20', '370', '2', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image13.jpg20200810_185127');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture High Waist Skirt', 'Duo-Texture produced.', '20', '52.75', '290', '2', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image14.jpg20200810_185136');


INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Short Sleeve T-shirt', 'Duo-Texture produced.', '3.50', '10.50', '1000', '3', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image15.jpg20200810_185219');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Harem Pants', 'Duo-Texture produced.', '2', '7.99', '130', '3', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image16.jpg20200810_185228');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Sleeveless T-shirt', 'Duo-Texture produced.', '6.70', '22.80', '490', '3', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image17.jpg20200810_185251');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Flower Floral Leggings', 'Duo-Texture produced.', '3.50', '10.50', '740', '3', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image18.jpg20200810_185310');
INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES('Duo Texture Cotton T-shirt', 'Duo-Texture produced.', '3', '7.99', '280', '3', 'https://jad-bucket.s3.us-east-2.amazonaws.com/image19.jpg20200810_185322'); 

-- Cart Table
CREATE TABLE `duotexture`.`carts` (
  `userId` INT NOT NULL,
  `productId` INT NOT NULL,
  `quantity` INT NOT NULL,
  FOREIGN KEY (`userId`) REFERENCES `duotexture`.`users` (`userId`) ON DELETE CASCADE,
  FOREIGN KEY (`productId`) REFERENCES `duotexture`.`products` (`productId`) ON DELETE CASCADE);
  
-- Orders Table
CREATE TABLE `duotexture`.`orders` (
  `userId` INT NOT NULL,
  `productId` INT NOT NULL,
  `quantity` INT NOT NULL,
  FOREIGN KEY (`userId`) REFERENCES `duotexture`.`users` (`userId`) ON DELETE CASCADE,
  FOREIGN KEY (`productId`) REFERENCES `duotexture`.`products` (`productId`) ON DELETE CASCADE);
  
-- Purchases Table
CREATE TABLE `duotexture`.`purchases` (
  `userId` INT NOT NULL,
  `productId` INT NOT NULL,
  `quantity` INT NOT NULL,
  FOREIGN KEY (`userId`) REFERENCES `duotexture`.`users` (`userId`) ON DELETE CASCADE,
  FOREIGN KEY (`productId`) REFERENCES `duotexture`.`products` (`productId`) ON DELETE CASCADE);

-- Card Table
CREATE TABLE `duotexture`.`cards` (
  `userId` INT NOT NULL,
  `cardOwner` VARCHAR(45) NOT NULL,
  `cardNumber` VARCHAR(45) NOT NULL,
  `expiryMonth` INT NOT NULL,
  `expiryYear` INT NOT NULL,
  `cvv` INT NOT NULL,
  FOREIGN KEY (`userId`) REFERENCES `duotexture`.`users` (`userId`) ON DELETE CASCADE
  );