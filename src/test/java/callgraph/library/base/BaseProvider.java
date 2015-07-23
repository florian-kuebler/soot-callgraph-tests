package callgraph.library.base;

public class BaseProvider {
	public static IBase ibase = new IBase() {

		private String s = "";

		@Override
		public IBase doSthArg(IBase object) {
			if (s.isEmpty())
				return object;
			else
				return null;
		}

		@Override
		public IBase doSth() {
			return this;
		}

		@Override
		public boolean checkSthArg(IBase object) {
			return object.toString().isEmpty();
		}

		@Override
		public boolean checkSth() {
			return s.isEmpty();
		}

		@Override
		public void changeSthArg(IBase object) {
			if (object == null) {
				s += "I";
			}
		}

		@Override
		public void changeSth() {
			s = "";
		}

		@Override
		public String toString() {
			return s;
		}
	};
}
