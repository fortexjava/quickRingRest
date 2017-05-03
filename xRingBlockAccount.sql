CREATE TABLE [dbo].[xRing_BlockAccount](
	[id] [bigint] primary key IDENTITY(1,1) NOT NULL,
	[account] [nvarchar](50) NOT NULL
);

create unique nonclustered index un_account_index on xRing_BlockAccount(account);