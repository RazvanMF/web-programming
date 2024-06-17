using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace ASPNET_Template.Models
{
    public class Model2
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        public string Field1 { get; set; }
        public int Field2 { get; set; }
        public DateTime Field3 { get; set; }
        public string Field4 { get; set; }
    }
}
