CREATE TABLE `PolyGroceManager`.`daily_uses` (
    id INT AUTO_INCREMENT,
    productName VARCHAR(255),
    amount VARCHAR(255),
    create_date DATE,
    update_date DATE,
    modified_by INT,
    PRIMARY KEY (id)
);
