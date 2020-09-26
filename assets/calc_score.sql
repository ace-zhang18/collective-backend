CREATE OR REPLACE FUNCTION CALC_SCORE (votes json) RETURNS numeric AS
$$
DECLARE
	upvoters bigint[];
	downvoters bigint[];
	score integer;
BEGIN
	SELECT array_agg(upvotes::bigint) FROM artworks a, LATERAL json_array_elements_text(rating->'upvotes') AS upvotes GROUP BY id INTO upvoters;
	SELECT array_agg(downvotes::bigint) FROM artworks a, LATERAL json_array_elements_text(rating->'downvotes') AS downvotes GROUP BY id INTO downvoters;
	score := array_length(upvoters, 1) - array_length(downvoters, 1);
	RETURN score;
END
$$ LANGUAGE plpgsql;