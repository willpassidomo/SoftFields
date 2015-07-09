# SoftFields

To use SoftFields, create a new instance of SoftQuestionsListAdapter, passing in a Context, a list of FieldValue objects and an optional Listener
```java
SoftQuestionsListAdapter sqla = new SoftQuestionsListAdapter(Context context, List<FieldValue> fvs, DataListener dl);
```
then add the SoftQuestionsListAdapter to your activities existing ListView element

## FieldValue
The FieldValue class describes a simple object which consists of a Field, a Value and boolean markers for "Required" and "In"

  - Field- denotes the field or "Question" and should not be blank
  - Value- represents the user's entry for the corresponding field. This should be blank initially, unless you wish for there to be a "default answer" of some sort
  - In- if true, means the FieldValue will apear in the list, if false, means the FieldValue will appear in the "Optional questions", accessible by hitting the "+" and selecting from the drop down.
  - Required- if true, means that the question is not able to be removed from "in", if false, may be removed or added. **IN NEEDS TO BE TRUE IF REQUIRED IS TRUE, NOT FOLLOWING THIS WILL RESULT IN UNDEFINED BEHAVIOR**

## DataListener
The Parent activity or whichever class is charged with tracking and/or storing the FieldValue pairs may implement the DataListener interface. Whenever a change is detected within the FieldValue pairs passed into the SoftQuestionListAdapter, the 
```java
public void setFieldValueData(List<FieldValue> fvs);
```
will be called

## Database Integration
A simple Database schema has been included as a subclass of the FieldValue class, consisting of two tables,
  - "StockFieldValues" which is used to store the starting state for a blank for (i.e which fields should be included in a new, blank instance of the form)
  - "FieldValues" Another which is used to store the FieldValues after the form has been submitted, with a UUID value to identify the form owner which they belong to.
