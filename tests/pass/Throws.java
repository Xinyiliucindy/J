package pass;

import java.lang.Exception;
import java.lang.Error;

public class Try {
	public int try_throw_error() {
		try {
			Error x = new Error();
			// throw x;

			return 0;
		} catch (Error e) {
			return 1;
		} catch (Exception e) {
			return 0;
		}

		return 0;
	}

	public int try_nothrow() {
		try {
			throw 1;

			return 0;
		} catch (Error | Exception e) {
			return 1;
		}

		return 0;
	}
}