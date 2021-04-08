# *Library Management Application*

## Proposal

***Description***
- *What will the application do?*
  - This application will provide users with the tools to organize and manage a library of books/ebooks/audiobooks.
  - You'll be allowed to add books to a library, see the contents of the books, edit the contents of the books, delete
    books from the library, and query for the books and sort them, borrow and put books on hold, and many other things 
    a library
    might require like signing out books by barcode, returning books, ordering books, maybe even selling books.
- *Who will use it?*
  - Anyone who has an interest in managing many books like librarians, book shops, and book collectors.
- *Why is this project of interest to you?*
  - I believe that this would be a good opportunity for learning. I think this would be a very practical project that
    has a very clear use. I believe that this would also be relevant to employers
    This project would help me practice create, read, update, delete operations. I think that working with databases is
    interesting, especially representing that database with an interface. I also enjoy reading and listening to books.
    
## User Stories

**Phase 1**
- As a user, I want to be able to multiple books to the library's catalogue
- As a user, I want to be able to view a list of books in the catalogue
- As a user, I want to be able to view the contents of a book
- As a user, I want to be able to update a book in the catalogue
- As a user, I want to be able to delete a book in the catalogue

**Phase 2**
- As a user, I want my library's catalogue to be saved automatically when I quit
- As a user, I want my library's catalogue to be loaded automatically when I start the app

**Phase 3**
- I've decided not to use autosave in my GUI and instead have buttons that load and save.

**Phase 4: Task 2**
- I will test and design my Library class to be robust
- addBook, removeBook, listBook, openBook, and updateBook have been made robust

**Phase 4: Task 3**
- My UML diagram appears to be very simple without a lot of associations between classes. This is probably because my 
  project is very simple. For the most part, there isn't a lot of refactoring I can do to improve the design. However,
  I could remove and replace the updateBook method in my Library class because that increases coupling between Library and Book
  and also decreases the cohesion of Library. The methods in my Library also have some duplication, which I could
  abstract. My LibraryGUI is a little messy, so I could probably break up some larger methods to improve
  readability. Besides these, I am satisfied with the design of my project
    