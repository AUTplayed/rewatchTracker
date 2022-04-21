package codes.fepi.entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Token implements Serializable {

	private static final byte VERSION_0 = 0;
	private static final byte CURRENT_VERSION = VERSION_0;

	private final byte version;
	private String userId;

	public Token() {
		this.version = CURRENT_VERSION;
	}

	public Token(String userId) {
		this();
		this.userId = userId;
	}

	public void writeObject(ObjectOutputStream out) throws IOException {
		out.writeByte(version);
		out.writeUTF(userId);
	}

	public void readObject(ObjectInputStream in)
			throws IOException, ClassNotFoundException {
		final byte version = in.readByte();
		if (VERSION_0 == version) {
			final String userId = in.readUTF();
			setUserId(userId);
			return;
		}
		throw new UnsupportedOperationException("unsupported token version " + version);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
