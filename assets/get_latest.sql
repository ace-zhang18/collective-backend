CREATE OR REPLACE FUNCTION GET_LATEST (history json) RETURNS numeric AS
$$
DECLARE
	latest timestamp;
BEGIN
	SELECT json_object_keys::timestamp FROM json_object_keys(history) INTO latest ORDER BY times desc LIMIT 1;
	RETURN latest;
END
$$ LANGUAGE plpgsql;