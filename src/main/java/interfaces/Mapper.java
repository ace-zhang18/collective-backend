package interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface Mapper {	
	@Select({"${sql}"})
	<T extends ObjectInterface> List<T> executeImmediate(String sql, T instance);
}
