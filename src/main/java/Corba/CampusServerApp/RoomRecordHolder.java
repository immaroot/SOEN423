package Corba.CampusServerApp;

/**
* Corba/CampusServerApp/RoomRecordHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 22, 2021 7:27:23 o'clock PM EDT
*/

public final class RoomRecordHolder implements org.omg.CORBA.portable.Streamable
{
  public Corba.CampusServerApp.RoomRecord value = null;

  public RoomRecordHolder ()
  {
  }

  public RoomRecordHolder (Corba.CampusServerApp.RoomRecord initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Corba.CampusServerApp.RoomRecordHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Corba.CampusServerApp.RoomRecordHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Corba.CampusServerApp.RoomRecordHelper.type ();
  }

}