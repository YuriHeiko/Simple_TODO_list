CREATE TABLE to_do_list.tasks (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  description VARCHAR(255),
  deadline DATETIME NOT NULL,
  alert DATETIME,
  state BIT(1),
  PRIMARY KEY (id));