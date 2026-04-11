ALTER TABLE type RENAME TO call_type;
ALTER TABLE call RENAME COLUMN type_id TO call_type_id;
ALTER TABLE call_type RENAME COLUMN name TO title;
ALTER TABLE call_type ADD COLUMN deadline NUMERIC(10,2) NOT NULL DEFAULT 0;