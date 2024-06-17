using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ASPNET_Template.Models
{
    public class Model1
    {
        [Key][DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        public string Field1 { get; set; }
        public int Field2 { get; set; }
        public DateTime Field3 { get; set; }
        public string Field4 { get; set; }

        // [ForeignKey("Model2")]
        // public int Model2Id { get; set; }
        // public Model2 Model2 { get; set; }
    }
}
