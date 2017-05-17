package server.model;

import java.util.UUID;

//класс для хранения куска файла, который кем-то  залочен и в каокм-то диапазоне
public class FileElement {
    private UUID Id;
    private ClientThread ct;
    private Range range;
    String username;

    FileElement(UUID Id, ClientThread ct, String username, Range range)
    {
        this.Id = Id;
        this.ct = ct;
        this.username = username;
        this.range = range;
    }
    public String getUsername() {
        return username;
    }
    public UUID getUUID()
    {
        return Id;
    }
    public int getStart()
    {
        return range.getStart();
    }
    public void setStart(int start)
    {
        range.setStart(start);
    }
    public int getEnd()
    {
        return range.getEnd();
    }
    public void setEnd(int end)
    {
        range.setEnd(end); 
    }
    ClientThread getCT()
    {
        return ct;
    }
}
