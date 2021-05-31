CREATE TABLE `reviewes` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`pid` BIGINT NOT NULL,
	`uid` BIGINT NOT NULL,
	`score` tinyint NOT NULL DEFAULT 5,
	`title` VARCHAR(50) NOT NULL,
	`body` text NOT NULL,
	`reply`text NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX (`pid`, `uid`),
	CONSTRAINT `FK_review_user` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `FK_review_product` FOREIGN KEY (`pid`) REFERENCES `products` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);


INSERT INTO `reviewes` (`pid`, `uid`, `score`, `title`, `body`, `reply`) VALUES
	(1,2,5, 'Love these!', 'I ordered these flowers along with the more popular Duovlo peonies. I have attached a picture showing both. The roses are smaller in size but I personally like them a lot better . I used a $1 glass bottle for the vase. I think it looks better in a smaller vase than a big one and you have to use both bouquets for a full look. I took out three flowers for a separate vase, so mine is not as full as it would be otherwise.','Thanks for the review, we appreciate'),
	(1,3,3, 'Flowers', 'Just flower, nothing more', null),
	(3,2,2, 'Thats good', 'Just flower, nothing more', null),
	(4,2,5, 'Roses Beautiful', 'Beautiful roses. I love them and would buy again.', null),
	(4,3,1, 'No!!!', 'Im upset!!!', null)

