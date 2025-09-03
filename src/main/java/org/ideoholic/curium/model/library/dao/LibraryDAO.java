package org.ideoholic.curium.model.library.dao;

import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.library.dto.Book;
import org.ideoholic.curium.model.library.dto.BookHistory;
import org.ideoholic.curium.model.library.dto.BookIssue;
import org.ideoholic.curium.repositories.BookHistoryRepository;
import org.ideoholic.curium.repositories.BookIssueRepository;
import org.ideoholic.curium.repositories.BookRepository;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LibraryDAO {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookIssueRepository bookIssueRepository;

    @Autowired
    private BookHistoryRepository bookHistoryRepository;

    @Transactional
    public Book create(Book book) {
        try {
            // session.save(book);
            return bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public List<Book> readListOfAvailableBook() {
        try {
            // session.createQuery("From Book").list();
            return bookRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Delete Book records (only where issuedqty = 0)
    @Transactional
    public void deleteRecord(List<Integer> ids) {
        try {
            // session.createQuery("delete from Book as book where issuedqty=0 and book.bid IN (:ids)");
            for (Integer id : ids) {
                Optional<Book> bookOpt = bookRepository.findById(id);
                if (bookOpt.isPresent() && bookOpt.get().getIssuedQty() == 0) {
                    bookRepository.deleteById(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Read list of Book by branchId
    @Transactional
    public List<Book> readListOfBook(String branchId) {
        try {
            // session.createQuery("From Book where branchid=" + branchId).list();
            return bookRepository.findByBranchid(Integer.parseInt(branchId));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Update book after issue (batch update issuedqty, add BookIssue and BookHistory)
    @Transactional
    public void updatebookAfterIssue(List<Integer> ids, List<BookHistory> bookHistoryList, List<BookIssue> bookIssueList) {
        try {
            // session.createSQLQuery("update book set issuedqty = issuedqty + 1  where bid IN (:ids)");
            for (Integer id : ids) {
                bookRepository.findById(id).ifPresent(book -> {
                	book.setIssuedQty(book.getIssuedQty() + 1);
                    bookRepository.save(book);
                });
            }
            bookIssueRepository.saveAll(bookIssueList);
            bookHistoryRepository.saveAll(bookHistoryList);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Read list of Books Issued by holder (sid)
    @Transactional
    public List<BookIssue> readListOfBooksIssued(String sid) {
        try {
            // session.createQuery("From BookIssue where bookHolder='" + sid + "'").list();
            return bookIssueRepository.findByBookHolder(sid);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Update book after return (batch decrement issuedQty)
    @Transactional
	public void updatebookAfterReturn(List<Integer> ids) {
		try {
			// session.createSQLQuery("update book set issuedQty = issuedQty-1 where bid IN (:ids)");
			for (Integer id : ids) {
				bookRepository.findById(id).ifPresent(book -> {
					book.setIssuedQty(book.getIssuedQty() - 1);
					bookRepository.save(book);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

    @Transactional
    public Book readDetailsOfBook(int bid) {
    	Book book = new Book();
        try {
            // session.createQuery("from Book as book where book.bid=" + bid);
        	book = bookRepository.findById(bid).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return book;
    }

    @Transactional
    public void updatebookdetail(int bid, String bookname, String subject, String author, String publisher, String isbn,
                                String shelf, int availableQty, int issuedQty) {
        try {
            // session.createSQLQuery("update book set bookname = '"+bookname+"' , subject = '"+subject+"' , author = '"+author+"' , publisher = '"+publisher+"' , isbn = '"+isbn+"' , shelf = '"+shelf+"', availableqty='"+availableQty+"', issuedqty='"+issuedQty+"' where bid ='"+bid+"'");
            bookRepository.findById(bid).ifPresent(book -> {
                book.setBookname(bookname);
                book.setSubject(subject);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setIsbn(isbn);
                book.setShelf(shelf);
                book.setAvailableQty(availableQty);
                book.setIssuedQty(issuedQty);
                bookRepository.save(book);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Read list of BookHistory between dates
    @Transactional
    public List<BookHistory> readListOfBookHistory(String fromDate, String toDate) {
        try {
            // session.createQuery("From BookHistory where issueDate between '" + fromDate + "' and '" + toDate + "'").list();
            return bookHistoryRepository.findByIssueDateBetween(fromDate, toDate);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public BookHistory add(BookHistory bookHistory) {
        try {
            // session.save(bookHistory);
            return bookHistoryRepository.save(bookHistory);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Delete BookHistory records by id list
    @Transactional
    public void deleteBookHistoryRecord(List<Integer> ids) {
        try {
            // session.createQuery("delete from BookHistory as book where book.id IN (:ids)");
            bookHistoryRepository.deleteAllById(ids);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public BookIssue add(BookIssue bookIssue) {
        try {
            // session.save(bookIssue);
            return bookIssueRepository.save(bookIssue);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Update BookIssue after return (delete BookIssue by id list)
    @Transactional
    public void updatebookissueAfterReturn(List<Integer> ids) {
        try {
            // session.createQuery("delete from BookIssue as book where book.id IN (:ids)");
            bookIssueRepository.deleteAllById(ids);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Update book status and details when issued
    @Transactional
    public void updatebook(String uid, List<Integer> ids, String date) {
        try {
            // session.createSQLQuery("update book set status = 'Issued', bookHolder = '" + uid + "', startdate = '" + date + "' where bid IN (:ids)");
            for (Integer id : ids) {
                bookRepository.findById(id).ifPresent(book -> {
                    book.setStatus("Issued");
                    book.setBookHolder(uid);
                    book.setStartdate(DateUtil.dateParserdd(date));
                    // Assume startdate is a String, convert as needed for Book entity
                    // book.setStartdate(...);
                    bookRepository.save(book);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Update Book on Return (decrement issuedQty, update BookIssue)
    @Transactional
    public void updateBookOnReturn(List<Integer> bookIds, List<Integer> bookIssueIds, List<Integer> noOfDays, String returnDate) {
        try {
            // session.createSQLQuery("update book set issuedQty = issuedQty-1  where bid IN (:ids)");
            for (Integer id : bookIds) {
                bookRepository.findById(id).ifPresent(book -> {
                	book.setIssuedQty(book.getIssuedQty() - 1);
                    bookRepository.save(book);
                });
            }
            // session.createSQLQuery("UPDATE bookissue SET noofdays = :totalDays, returned = 'Yes',actualreturndate='"+returnDate+"' WHERE id = :bookId");
            for (int i = 0; i < bookIssueIds.size(); i++) {
                int issueId = bookIssueIds.get(i);
                int days = noOfDays.get(i);
                Optional<BookIssue> issueOpt = bookIssueRepository.findById(issueId);
                if (issueOpt.isPresent()) {
                    BookIssue issue = issueOpt.get();
                    issue.setNoOfDays(days);
                    issue.setReturned("Yes");
                    // Assume actualReturnDate is a String, convert as needed
                    // issue.setActualReturnDate(...);
                    bookIssueRepository.save(issue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}