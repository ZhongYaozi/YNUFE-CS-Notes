CREATE TABLE bmb (
    bh CHAR(4) PRIMARY KEY,
    bmm VARCHAR(20) NOT NULL,
    dh CHAR(4) NULL CHECK (dh LIKE '1%')
);

CREATE TABLE ysb (
    bh CHAR(3) PRIMARY KEY,
    xm NCHAR(4),
    bmbh CHAR(4) DEFAULT '1111',
    FOREIGN KEY (bmbh) REFERENCES bmb(bh)
);

CREATE TABLE ypb (
    bh CHAR(5) PRIMARY KEY,
    ypm VARCHAR(30) NOT NULL,
    jg MONEY
);

CREATE TABLE cfb (
    ysbh CHAR(3),
    ypbh CHAR(5),
    kfrq DATE,
    ypsl SMALLINT DEFAULT 0,
    PRIMARY KEY (ysbh, ypbh, kfrq),
    FOREIGN KEY (ysbh) REFERENCES ysb(bh),
    FOREIGN KEY (ypbh) REFERENCES ypb(bh)
);
