using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using SDP.Data;
var builder = WebApplication.CreateBuilder(args);
builder.Services.AddDbContext<SDPContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("SDPContext") ?? throw new InvalidOperationException("Connection string 'SDPContext' not found.")));

builder.Services.AddSingleton<IHttpContextAccessor, HttpContextAccessor>();

// Add services to the container.
builder.Services.AddControllersWithViews();

builder.Services.AddDistributedMemoryCache();

builder.Services.AddSession(options =>
{
    options.IdleTimeout = TimeSpan.FromDays(1);
    options.Cookie.HttpOnly = true;
    options.Cookie.IsEssential = true;
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
	app.UseExceptionHandler("/Home/Error");
}
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.UseSession();

app.MapControllerRoute(
	name: "default",
	pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
