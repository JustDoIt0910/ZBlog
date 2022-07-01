create table article
(
	`id` int auto_increment,
    `cid` int not null comment '文章所属分类id',
    `banner` varchar(100) not null comment '配图',
    `isTop` bool default false comment '是否置顶',
    `pubTime` datetime not null comment '发布日期',
    `title` varchar(100) not null,
    `summary` varchar(255) not null,
    `content` text not null,
    `viewsCount` int default 0,
    `commentsCount` int default 0,
    primary key(`id`)
);

create table category
(
	`id` int auto_increment,
    `name` varchar(50) not null,
    primary key(`id`)
);

-- 友链
create table friend
(
	`id` int auto_increment,
    `siteName` varchar(100) not null,
    `path` varchar(100) not null,
    `desc` varchar(255) not null,
    primary key(`id`)
);

create table `comment`
(
	`id` int auto_increment,
    `postId` int not null comment '评论所属文章id',
    `parentId` int default null comment '父级评论id',
    `fromUserId` int default null,
    `toUserId` int default null,
    `content` text not null,
    `createTime` datetime not null,
    primary key(`id`)
);

create table `user`
(
	`id` int auto_increment,
    `username` varchar(50) not null,
    `email` varchar(50) default null,
    `avatar` blob not null,
    primary key(`id`)
);