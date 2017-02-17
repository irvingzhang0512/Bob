DROP TABLE t_raw_local;
CREATE TABLE t_raw_local(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    meter_number VARCHAR(12),
    insert_time DATETIME,
    voltage DECIMAL(5,2),
    current DECIMAL(5,2),
    active_power DECIMAL(7,2),
    reactive_ower DECIMAL(7,2),
    apparent_power DECIMAL(7,2),
    power_factor DECIMAL(3,2),
    electric_energy DECIMAL(8,2)
);

DROP TABLE t_local_data;
CREATE TABLE t_local_data (
    id int AUTO_INCREMENT PRIMARY KEY,
    user_id int NOT NULL,
    meter_number nvarchar(20) NOT NULL,
    year smallint NOT NULL,
    month smallint NOT NULL,
    day smallint NOT NULL,
    hour smallint NOT NULL,
    minute smallint NOT NULL,

    current double,
    voltage double,
    electricEnergy double,
    activePower double,
    reactivePower double,
    apparentPower double,
    powerFactor double,

    status smallint
);