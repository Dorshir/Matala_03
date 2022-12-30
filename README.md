# Observer design pattern via UndoableStringBuilder

## Classes : 
 `GroupAdmin` , `ConcerteMember` , `UndoableStringBuilder`


## Introduction

Implement the `UndoableStringBuilder` as observer desgin pattern.

In order to fulfil Observer design pattern, we use `GroupAdmin` as subject and `ConcerteMember` as observers.

Each observer will get a shallow copy of the subject's `UndoableStringBuilder` object, which will notify at any state changes from the subject.

#### UndoableStringBuilder

Class that extends the functionality of `StringBuilder` by adding an option to undo the recent operation.

#### GroupAdmin

Class that is used as the subject. Therefore it has a list of members(`ArrayList`) and the product(`UndoableStringBuilder`). 

Hold the methods: 
- register() 
- unregister()
- notifyObservers()

#### ConcerteMember

Class that is used as the observer. Therefore, it has a shallow copy of the subject's proudct(`UndoableStringBuilder`). 

> note :`ConcertMember` can only be linked to one `GroupAdmin` at a time.

Hold the method: 
- update()


## Usage

In order to trace the memory useage and addresses of the objects we work on, we used `JvmUtilities`.


## Authors

 - [@dorshir](https://www.github.com/dorshir)
 - [@barmud](https://www.github.com/barmud)
