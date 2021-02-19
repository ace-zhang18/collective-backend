package interfaces;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import objects_misc.ReactionCount;

public interface Mapper {	
	//Can only get IDs but can order, limit, and offset them
	@Select({"${sql}"})
	public long[] executeImmediate(@Param("sql") String sql);
	
	@Select({"SELECT reaction, COUNT(reaction) FROM public.post_user_reactions WHERE post_source = '${source}' AND post_id = ${post_id} GROUP BY reaction;"})
	@Results(value = {
			  @Result(property = "reaction", column = "reaction"),
			  @Result(property= "count", column = "count")
			})
	public ReactionCount[] getPostReactionCounts(@Param("source") String source, @Param("post_id") long post_id);
	
	
	@Select({"SELECT id FROM public.replies WHERE \"source\" = '${source}' AND source_id = ${source_id} ORDER BY timestamp;"})
	public long[] getReplies(@Param("source") String source, @Param("source_id") long source_id);
}
