using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Models
{
    [Table("GameCreator")]
    public class GameCreator
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid ID { get; set; }
        [ForeignKey("Player")]
        public Guid PlayerId { get; set; }
        [ForeignKey("Room")]
        public Guid RoomId { get; set; }


    }
}
