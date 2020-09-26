CREATE OR REPLACE FUNCTION COMPARE_JSON (json1 json, json2 json) RETURNS BOOLEAN AS 
$$
DECLARE
	keys1 text[];
	keys2 text[];
	array1 json[];
	array2 json[];
	type1 text;
	type2 text;
	match_found boolean;
BEGIN
	type1 := json_typeof(json1);
	type2 := json_typeof(json2);
	
	IF type1 != type2 THEN
		RETURN FALSE;
	END IF;
	IF type1 = 'number' OR type1 = 'string' OR type1 = 'boolean' OR type1 = 'null' THEN
		IF json1::text != json2::text THEN	
			RETURN FALSE;
		END IF;
	ELSEIF type1 = 'array' THEN
		SELECT array_agg(obj) FROM LATERAL json_array_elements(json1) AS obj INTO array1;
		SELECT array_agg(obj) FROM LATERAL json_array_elements(json2) AS obj INTO array2;
		FOR x IN 1..array_upper(array1, 1) LOOP
			IF NOT compare_json(array1[x], array2[x]) THEN
				RETURN FALSE;
			END IF;
		END LOOP;
	ELSEIF type1 = 'object' THEN
		SELECT array_agg(keys) FROM json_object_keys(json1::json) AS keys INTO keys1;
		SELECT array_agg(keys) FROM json_object_keys(json2::json) AS keys INTO keys2;

		--keys are always a unique string
		SELECT ARRAY(SELECT unnest(keys1) ORDER BY 1) INTO keys1;
		SELECT ARRAY(SELECT unnest(keys2) ORDER BY 1) INTO keys2;
	
		IF array_length(keys1, 1) != array_length(keys2, 1) THEN
			RETURN FALSE;
		END IF;

		FOR x IN 1..array_upper(keys1, 1) LOOP
			IF keys1[x] != keys2[x] THEN
				RETURN FALSE;
			END IF;
		END LOOP;
		FOR x IN 1..array_upper(keys1, 1) LOOP
			--RAISE NOTICE '%, % - %, % JSON COMP', json1->keys1[x], json_typeof(json1->keys1[x]), json2->keys2[x], json_typeof(json2->keys2[x]);
			IF COMPARE_JSON(json1->keys1[x], json2->keys2[x]) != TRUE THEN
				RETURN FALSE;
			END IF;
		END LOOP;
	END IF;
	RETURN TRUE;
END
$$ LANGUAGE plpgsql;