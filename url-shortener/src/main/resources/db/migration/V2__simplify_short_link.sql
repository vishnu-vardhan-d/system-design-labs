ALTER TABLE short_link DROP COLUMN code;
ALTER TABLE short_link MODIFY id DEFAULT short_link_id_seq.NEXTVAL;