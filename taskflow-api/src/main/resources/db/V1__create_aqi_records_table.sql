CREATE TABLE `aqi_records` (
`id` INT NOT NULL AUTO_INCREMENT,
`station_name` VARCHAR(50) NOT NULL,
`county` VARCHAR(50) NOT NULL,
`aqi` INT NOT NULL,
`status` VARCHAR(20) NOT NULL,
`pm25` INT NOT NULL,
`publish_time` DATETIME NOT NULL,
`created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
INDEX `idx_station_name` (`station_name`)
);