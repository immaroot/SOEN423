package CampusServerApp;


/**
* CampusServerApp/TimeSlot.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 22, 2021 3:37:47 o'clock PM EDT
*/

public final class TimeSlot implements org.omg.CORBA.portable.IDLEntity
{
  public CampusServerApp.Time start = null;
  public CampusServerApp.Time end = null;

  public TimeSlot ()
  {
  } // ctor

  public TimeSlot (CampusServerApp.Time _start, CampusServerApp.Time _end)
  {
    start = _start;
    end = _end;
  } // ctor

} // class TimeSlot
