/**
 * 
 */
package com.model.order.booksinfo.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.order.booksinfo.service.BooksInfoService;

/**
 * @author Musaib_2
 * 
 */
public class BooksInfoAction {

        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession httpSession;
        String url;

        public BooksInfoAction(HttpServletRequest request,
                        HttpServletResponse response) {
                this.request = request;
                this.response = response;
                this.httpSession = request.getSession();
        }

        public String execute(String action) {
                // TODO Auto-generated method stub
                if (action.equalsIgnoreCase("addBooksInfo")) {
                        url = addBooksInfo();
                }else if (action.equalsIgnoreCase("deleteBooksInfo")) {
                    	url = deleteBooksInfo();
                }else if (action.equalsIgnoreCase("viewBooksInfo")) {
                    url = viewBooksInfo();
                }else if (action.equalsIgnoreCase("getAuthor")) {
                      getAuthor();
                }    
                return url;
        }
        


		private void getAuthor() {
			
			try {
				new BooksInfoService(request, response).getAuthor();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private String deleteBooksInfo() {
        	new BooksInfoService(request, response).deleteMultipleBooksInfo();
            return viewBooksInfo();
		}

		private String addBooksInfo() {
   		 new BooksInfoService(request, response).addBookInfoDetails();
   		 return viewBooksInfo();
   }

		private String viewBooksInfo() {
			return new BooksInfoService(request, response).viewBooksInfoDetails();
		}

		

}
