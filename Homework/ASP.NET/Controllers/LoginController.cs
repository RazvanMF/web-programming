using Lab10WebAssignment.Data;
using Lab10WebAssignment.Models;
using Microsoft.AspNetCore.Mvc;

namespace Lab10WebAssignment.Controllers
{
	public class LoginController : Controller
	{
		private readonly WebDatabaseContext _context;

		public LoginController(WebDatabaseContext context)
		{
			_context = context;
		}

		public IActionResult LoginAndRedirect(User user)
		{
			User? result = _context.User.Where(element => element.Username == user.Username && element.Password == user.Password).FirstOrDefault();
			if (result == null)
				return RedirectToAction("Error", "Login");

			HttpContext.Session.SetInt32("UserID", result.Id);
			BooksController.logs.Clear();
			return RedirectToAction("Index", "Books");
		}

		public IActionResult LogoutAndRedirect() {
			HttpContext.Session.Clear();
            BooksController.logs.Clear();
            return RedirectToAction("Index", "Home");
		}

		public IActionResult Error() {
			return View();
		}
	}
}
