package CampusServerApp;


/**
* CampusServerApp/Time.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 22, 2021 3:37:47 o'clock PM EDT
*/

public final class Time implements org.omg.CORBA.portable.IDLEntity
{
  public short hour = (short)0;
  public short minute = (short)0;

  public Time ()
  {
  } // ctor

  public Time (short _hour, short _minute)
  {
    hour = _hour;
    minute = _minute;
  } // ctor

} // class Time
