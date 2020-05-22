using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Models
{
    [Table("GameSession")]
    public class GameSession
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid GameSessionId { get; set; }
        public TimeSpan GameTime { get; set; }
        public TimeSpan VoteTime { get; set; }
        [ForeignKey("Room")]
        public Guid RoomId { get; set; }
    }
}
