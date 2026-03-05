# JohnChatBot User Guide

JohnChatBot is a command-line chatbot that helps you manage your tasks. It supports to-dos, deadlines, and events, and saves your data automatically between sessions.

## Adding a to-do

Adds a basic task with no date or time attached.

Example: `todo DESCRIPTION`

```
todo buy groceries
```

Expected output:

```
Got it. I've added this task:
  [T][ ] buy groceries
```

## Adding a deadline

Adds a task with a specific due date or time by using the `/by` flag.

Example: `deadline DESCRIPTION /by DATE`

```
deadline submit assignment /by Sunday 11:59pm
```

Expected output:

```
Got it. I've added this task:
  [D][ ] submit assignment (by: Sunday 11:59pm)
```

## Adding an event

Adds a task with a start and end time by using the `/from` and `/to` flags.

Example: `event DESCRIPTION /from START /to END`

```
event team meeting /from Mon 2pm /to Mon 4pm
```

Expected output:

```
Got it. I've added this task:
  [E][ ] team meeting (from: Mon 2pm to: Mon 4pm)
```

## Listing all tasks

Displays all tasks currently in your list.

Example: `list`

```
list
```

Expected output:

```
1. [T][ ] buy groceries
2. [D][ ] submit assignment (by: Sunday 11:59pm)
3. [E][ ] team meeting (from: Mon 2pm to: Mon 4pm)
```

## Marking a task as done

Marks a task as completed using its index number shown in `list`.

Example: `mark INDEX`

```
mark 1
```

Expected output:

```
Nice! I've marked this task as done:
  [T][X] buy groceries
```

## Unmarking a task

Marks a previously completed task as not done, using its index number shown in `list`.

Example: `unmark INDEX`

```
unmark 1
```

Expected output:

```
OK, I've marked this task as not done yet:
  [T][ ] buy groceries
```

## Deleting a task

Permanently removes a task from the list using its index number shown in `list`.

Example: `delete INDEX`

```
delete 2
```

Expected output:

```
Noted. I've removed this task:
  [D][ ] submit assignment (by: Sunday 11:59pm)
Now you have 2 tasks in the list.
```

## Finding tasks by keyword

Searches for all tasks whose descriptions contain the given keyword.

Example: `find KEYWORD`

```
find meeting
```

Expected output:

```
Here are the matching tasks:
1. [E][ ] team meeting (from: Mon 2pm to: Mon 4pm)
```

## Exiting the application

Exits JohnChatBot. Your task list is saved automatically before the application closes.

Example: `bye`

```
bye
```

Expected output:

```
Bye. Hope to see you again soon, chief!
```
