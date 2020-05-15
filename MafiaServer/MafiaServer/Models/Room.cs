using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Models
{
    [Table("Room")]
    public class Room
    {
        [Key]
        public Guid ID { get; set; }
        [ForeignKey("Player")]
        public List<Player> Players { get; set; }
        [ForeignKey("GameCreator")]
        public Guid GameCreatorId { get; set; }
        [ForeignKey("GameSession")]
        public Guid GameSessionId { get; set; }
    }
}
