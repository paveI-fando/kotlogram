package com.github.badoualy.telegram.tl.api.account;

import com.github.badoualy.telegram.tl.TLContext;
import com.github.badoualy.telegram.tl.core.TLBytes;
import com.github.badoualy.telegram.tl.core.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.github.badoualy.telegram.tl.StreamUtils.readInt;
import static com.github.badoualy.telegram.tl.StreamUtils.readTLBytes;
import static com.github.badoualy.telegram.tl.StreamUtils.readTLString;
import static com.github.badoualy.telegram.tl.StreamUtils.writeInt;
import static com.github.badoualy.telegram.tl.StreamUtils.writeString;
import static com.github.badoualy.telegram.tl.StreamUtils.writeTLBytes;

/**
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
public class TLPasswordInputSettings extends TLObject {
    public static final int CONSTRUCTOR_ID = 0xbcfc532c;

    protected int flags;

    protected TLBytes newSalt;

    protected TLBytes newPasswordHash;

    protected String hint;

    protected String email;

    public TLPasswordInputSettings() {
    }

    public TLPasswordInputSettings(TLBytes newSalt, TLBytes newPasswordHash, String hint, String email) {
        this.newSalt = newSalt;
        this.newPasswordHash = newPasswordHash;
        this.hint = hint;
        this.email = email;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        flags = 0;
        flags = newSalt != null ? (flags | 1) : (flags &~ 1);
        flags = newPasswordHash != null ? (flags | 1) : (flags &~ 1);
        flags = hint != null ? (flags | 1) : (flags &~ 1);
        flags = email != null ? (flags | 2) : (flags &~ 2);

        writeInt(flags, stream);
        if ((flags & 1) != 0) writeTLBytes(newSalt, stream);
        if ((flags & 1) != 0) writeTLBytes(newPasswordHash, stream);
        if ((flags & 1) != 0) writeString(hint, stream);
        if ((flags & 2) != 0) writeString(email, stream);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = readInt(stream);
        if ((flags & 1) != 0) newSalt = readTLBytes(stream, context);
        if ((flags & 1) != 0) newPasswordHash = readTLBytes(stream, context);
        if ((flags & 1) != 0) hint = readTLString(stream);
        if ((flags & 2) != 0) email = readTLString(stream);
    }

    @Override
    public String toString() {
        return "account.passwordInputSettings#bcfc532c";
    }

    @Override
    public int getConstructorId() {
        return CONSTRUCTOR_ID;
    }

    public TLBytes getNewSalt() {
        return newSalt;
    }

    public void setNewSalt(TLBytes newSalt) {
        this.newSalt = newSalt;
    }

    public TLBytes getNewPasswordHash() {
        return newPasswordHash;
    }

    public void setNewPasswordHash(TLBytes newPasswordHash) {
        this.newPasswordHash = newPasswordHash;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
