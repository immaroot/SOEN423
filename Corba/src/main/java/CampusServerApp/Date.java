package CampusServerApp;


/**
* Corba/CampusServerApp/Date.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 29, 2021 3:13:20 o'clock PM EDT
*/

public final class Date implements org.omg.CORBA.portable.IDLEntity
{
  public int year = (int)0;
  public int month = (int)0;
  public int day = (int)0;

  public Date ()
  {
  } // ctor

  public Date (int _year, int _month, int _day)
  {
    year = _year;
    month = _month;
    day = _day;
  } // ctor

} // class Date
