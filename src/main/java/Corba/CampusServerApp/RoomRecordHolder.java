package CampusServerApp;

/**
* CampusServerApp/RoomRecordHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 22, 2021 3:37:47 o'clock PM EDT
*/

public final class RoomRecordHolder implements org.omg.CORBA.portable.Streamable
{
  public CampusServerApp.RoomRecord value = null;

  public RoomRecordHolder ()
  {
  }

  public RoomRecordHolder (CampusServerApp.RoomRecord initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CampusServerApp.RoomRecordHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CampusServerApp.RoomRecordHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CampusServerApp.RoomRecordHelper.type ();
  }

}
