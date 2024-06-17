namespace Lab10WebAssignment.Models
{
    public class Log
    {
        public int Id { get; set; }

        public string? TitleFilter { get; set; } = null;
        public string? AuthorFilter { get; set; } = null;
        public string? GenreFilter { get; set; } = null;
    }
}
