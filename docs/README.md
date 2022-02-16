# User Guide

Welcome to Ume, an energetic chat-bot who remembers tasks for you.

## Features

### Remembers tasks

Tell Ume what tasks you have, and she will keep them in mind.

### Manage tasks

Ume lets you check tasks off your list, delete them, sort by date and time if any, and so on.

## Usage

### `todo`, `event`, `deadline` - Create tasks

Creates the respective tasks and adds them into Ume's list.

Events have a date field for when it occurs, while deadlines have a date field for the deadline. Todo does not record a date.

Example of usage: 

- `todo DESCRIPTION`
- `event DESCRIPTION/TIME`
- `deadline DESCRIPTION/TIME`

Fill the time field in the form of `HHMM DDMMYYYY`, 24-hour format.

Ume will echo the task created.

```
deadline Big homework/2359 01032022

That looks urgent.
[D][ ]Big homework (by 11:59PM, 01 Mar 2022)
```
### `list` - See all your tasks

Ume shows all the tasks in her list.


```
list

1: [D][ ]Big homework (by 11:59PM, 01 Mar 2022)
```

### `mark`, `unmark`, `delete` - Check tasks off

Tasks can be marked complete or incomplete. If you're done with them, you can remove them too.

Example of usage:

- `mark INDEX`
- `unmark INDEX`
- `delete INDEX`

First use `list` to see what index the task is at.

### `find` - Find tasks

Too many tasks? Find the task you're looking for by searching a string pattern.

Example of usage:

- `find STRINGTOSEARCH`

The string does not need to be a whole word.

```
find work

Looking for these?
1: [D][ ]Big homework (by 11:59PM, 01 Mar 2022)

find meal

No tasks found!
```

### `sort` - Reorder how tasks are displayed

Ume will sort the tasks in her list by the given order.

Example of usage:

- `sort ORDER`

Currently, supported orders include `type` and `date`.