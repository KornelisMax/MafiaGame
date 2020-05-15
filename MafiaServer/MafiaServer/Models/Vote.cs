using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Models
{
    [Table("Vote")]
    public class Vote
    {
        [Key]
        public Guid VoteId { get; set; }
        [ForeignKey("Player")]
        public Guid VotingPlayerId { get; set; }
        [ForeignKey("Player")]
        public Guid VotedPlayerId { get; set; }
    }
}
